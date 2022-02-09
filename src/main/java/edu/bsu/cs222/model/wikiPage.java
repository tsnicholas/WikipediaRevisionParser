package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class wikiPage {
    private final URL wikiURL;

    public wikiPage(String wikiName) throws MalformedURLException {
        wikiURL = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + wikiName + "&rvprop=timestamp|user&rvlimit=4&redirects");
    }

    // TODO: Should throw some kind of error when there's a network error
    public void getRevisions() {
        try {
            URLConnection connection = wikiURL.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; tsnicholas@bsu.edu, acmiller@bsu.edu)");
            InputStream inputStream = connection.getInputStream();
            revisionParser revisionparser = new revisionParser();
        }
        catch (IOException e) {
            ErrorWindow error = new ErrorWindow("An error has occurred.");
            error.displayError();
        }
    }
}
