package edu.bsu.cs222.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikiPageReader {
    private final URL wikiURL;
    private URLConnection connection;

    public WikiPageReader(String wikiName) throws MalformedURLException {
        String encodedName = URLEncoder.encode(wikiName, Charset.defaultCharset());
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=30&redirects",
                encodedName);
        wikiURL = new URL(urlString);
    }

    public void connect() throws IOException {
        connection = wikiURL.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; acmiller@bsu.edu, tsnicholas@bsu.edu)");
        System.out.println(connection.getInputStream());
    }

    public String getRevisions() throws IOException {
        RevisionData revisions = new RevisionData(connection.getInputStream());
        return revisions.toString();
    }
}
