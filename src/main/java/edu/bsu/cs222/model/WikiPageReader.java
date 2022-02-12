package edu.bsu.cs222.model;

import edu.bsu.cs222.exceptions.NetworkErrorException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WikiPageReader {
    private final WikiPageParser parser = new WikiPageParser();
    private final URL wikiURL;
    private URLConnection connection;

    public WikiPageReader(String wikiName) throws MalformedURLException {
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=30&redirects",
                wikiName);
        wikiURL = new URL(urlString);
    }

    public void connectToNetwork() throws NetworkErrorException {
        try {
            connection = wikiURL.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; tsnicholas@bsu.edu, acmiller@bsu.edu)");
        }
        catch(IOException networkError) {
            throw new NetworkErrorException("Network error has occurred", networkError);
        }
    }

    public boolean pageExists() throws IOException {
        try {
            return parser.parseForPageID(connection.getInputStream());
        }
        catch(IOException checkFailed) {
            throw new IOException();
        }
    }

    public RevisionData retrieveRevisionData() throws IOException {
        String[] timestamps = parser.parseTimestamps(connection.getInputStream());
        String[] usernames = parser.parseUsers(connection.getInputStream());
        String redirect = retrieveRedirectInfo();
        return new RevisionData(timestamps, usernames, redirect);
    }

    private String retrieveRedirectInfo() {
        try {
            return "Redirected to " + parser.parseRedirect(connection.getInputStream()) + "\n";
        }
        catch(IOException noRedirect) {
            return "";
        }
    }
}
