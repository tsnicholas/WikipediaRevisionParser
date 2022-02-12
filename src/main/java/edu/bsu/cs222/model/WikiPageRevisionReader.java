package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WikiPageRevisionReader {
    private final WikiPageParser parser = new WikiPageParser();
    private final URL wikiURL;
    private URLConnection connection;
    private String[] timestamps;
    private String[] usernames;
    private String redirect = "";

    public WikiPageRevisionReader(String wikiName) throws MalformedURLException {
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=30&redirects",
                wikiName);
        wikiURL = new URL(urlString);
        startProcess();
    }

    private void startProcess() {
        connectToNetwork();
        retrieveRedirectName();
        if(pageExists()) {
            getRevisions();
        }
    }


    private void connectToNetwork() {
        try {
            connection = wikiURL.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; tsnicholas@bsu.edu)");
        }
        catch(IOException networkError) {
            System.err.println("Network Error: " + networkError.getMessage());
            ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred!");
            networkErrorWindow.displayError();
            System.exit(0);
        }
    }

    private void retrieveRedirectName() {
        try {
            redirect = "Redirected to " + parser.parseRedirect(connection.getInputStream()) + "\n";
        }
        catch(IOException noRedirect) {
            return;
        }
    }

    private boolean pageExists() {
        try {
            return parser.parseMissing(connection.getInputStream());
        }
        catch(IOException checkFailed) {
            shootError(checkFailed);
        }

        return false;
    }

    private void getRevisions() {
            try {
                timestamps = parser.parseTimestamps(connection.getInputStream());
                usernames = parser.parseUsers(connection.getInputStream());
            }
            catch(IOException ioException) {
                shootError(ioException);
            }
    }

    private void shootError(Exception e) {
        System.err.println("Error: " + e.getMessage());
        ErrorWindow errorWindow = new ErrorWindow("An error has occurred!");
        errorWindow.displayError();
        System.exit(0);
    }

    public String toString() {
        if(!pageExists()) {
            return "This wikipedia page doesn't exist";
        }

        StringBuilder organizedData = new StringBuilder();
        organizedData.append(redirect);

        for(int i = 0; i < timestamps.length; i++) {
            organizedData.append(usernames[i]);
            organizedData.append(" ");
            organizedData.append(timestamps[i]);
            organizedData.append("\n");
        }

        return organizedData.toString();
    }
}
