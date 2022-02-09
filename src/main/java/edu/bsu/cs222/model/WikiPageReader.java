package edu.bsu.cs222.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WikiPageReader {

    public String urlTemplate = ("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=soup&rvprop=timestamp|user&rvlimit=1&redirects");

    private final URL wikiURL;
    private URLConnection connection;

    public WikiPageReader(String wikiName) throws MalformedURLException {
        wikiURL = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + wikiName + "&rvprop=timestamp|user&rvlimit=30&redirects");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try {
            String timestamp = this.getLatestRevisionOf(line);
            System.out.println(timestamp);
        } catch (IOException IOException) {
            System.err.println("Network connection problem: " + IOException.getMessage());
        }
    }

    public void connect() throws Exception {
        connection = wikiURL.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; acmiller@bsu.edu, tsnicholas@bsu.edu)");
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException {
        try{
            InputStream inputStream = connection.getInputStream();
            WikiPageParser parser = new WikiPageParser();
            String timestamp = parser.parse(inputStream);
            return timestamp;
        } catch (MalformedURLException malformedURLException){
            throw new MalformedURLException();
        }
    }
}
