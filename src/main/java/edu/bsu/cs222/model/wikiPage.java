package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class wikiPage {
    private final URL wikiURL;
    private URLConnection connection;

    public wikiPage(String wikiName) throws MalformedURLException {
        wikiURL = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + wikiName + "&rvprop=timestamp|user&rvlimit=4&redirects");
    }

    public void getRevisions() {
        try {
            connection = wikiURL.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; tsnicholas@bsu.edu, acmiller@bsu.edu)");
            parseRevisions();
        }
        catch (IOException e) {
            ErrorWindow error = new ErrorWindow("An error has occurred.");
            error.displayError();
        }
    }

    private void parseRevisions() throws IOException {
        InputStream inputStream = connection.getInputStream();
        revisionParser parser = new revisionParser();
        parser.parse(inputStream);
    }
}
