package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.net.MalformedURLException;
import java.net.URL;

public class wikiPage {
    private URL wiki;

    public wikiPage() {

    }

    public void obtainWikiURL(String wikipediaName) {
        try {
            wiki = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + wikipediaName + "&rvprop=timestamp|user&rvlimit=30&redirects");
        }
        catch (MalformedURLException e) {
            ErrorWindow URLError = new ErrorWindow(wikipediaName + " doesn't exist");
            URLError.displayError();
        }
    }
}
