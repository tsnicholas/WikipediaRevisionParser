package edu.bsu.cs222.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class wikiPage {
    private URL wiki;
    private URLConnection connection;

    public wikiPage() {

    }

    // TODO: create this function to return the correct revision URL of the name inputted
    public void findRevisionURL(String wikipediaName) throws Exception {
        try {
            wiki = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + wikipediaName + "&rvprop=timestamp|user&rvlimit=30&redirects");
        }
        catch (MalformedURLException e) {
            throw new Exception();
        }
    }
}
