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



    public static void main(String[] args) {
        WikiPageReader revisionReader = new WikiPageReader();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try {
            String timestamp = revisionReader.getLatestRevisionOf(line);
            System.out.println(timestamp);
        } catch (IOException IOException) {
            System.err.println("Network connection problem: " + IOException.getMessage());
        }
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException {
        String urlString = String.format(urlTemplate, articleTitle);
        String encodedUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try{
            URL url = new URL(encodedUrlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; acmiller@bsu.edu)");
            InputStream inputStream = connection.getInputStream();
            WikiPageParser parser = new WikiPageParser();
            String timestamp = parser.parse(inputStream);
            return timestamp;
        } catch (MalformedURLException malformedURLException){
            throw new MalformedURLException();
        }
    }


//     TODO: create this class to return the correct revision URL of the name inputted
//    public URL findRevisionURL(String wikipediaName) throws MalformedURLException {
//      throw new MalformedURLException();
//    }
}
