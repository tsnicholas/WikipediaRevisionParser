package edu.bsu.cs222.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Scanner;

public class WikiPageReader {
    private final URL wikiURL;
    private URLConnection connection;
    private HashMap<String, String> revisions;

    public WikiPageReader(String wikiName) throws MalformedURLException {
        wikiURL = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + wikiName + "&rvprop=timestamp|user&rvlimit=30&redirects");
    }

    public void connect() throws Exception {
        connection = wikiURL.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; acmiller@bsu.edu, tsnicholas@bsu.edu)");
    }

    public void getRevisions() throws IOException {
        InputStream inputStream = connection.getInputStream();
        WikiPageParser parser = new WikiPageParser();
        revisions = parser.parse(inputStream);
    }

}
