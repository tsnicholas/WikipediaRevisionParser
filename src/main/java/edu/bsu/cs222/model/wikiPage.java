package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.net.MalformedURLException;
import java.net.URL;

public class wikiPage {
    private URL wikiURL;

    public wikiPage(String wikiName) throws MalformedURLException {
        wikiURL = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + wikiName + "&rvprop=timestamp|user&rvlimit=4&redirects");
    }

    public void createConnection() {

    }

}
