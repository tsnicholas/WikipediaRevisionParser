package edu.bsu.cs222.model;

import edu.bsu.cs222.exceptions.NetworkErrorException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WikiPageReader {
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

    public RevisionData retrieveRevisionData() throws IOException {
        return new RevisionData(connection.getInputStream());
    }
}
