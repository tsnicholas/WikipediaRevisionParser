package edu.bsu.cs222;

import edu.bsu.cs222.model.WikiPageParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

import net.minidev.json.JSONArray;


public class WikiPageParserTest {
    private final InputStream soupStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("soupTest.json");
    private final InputStream testStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testRedirectedPage.json");
    private final InputStream invalidStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testInvalidPage.json");
    private final WikiPageParser parser = new WikiPageParser();

    @Test
    public void testParseTimestamps() throws IOException {
        JSONArray expectedTimestamps = new JSONArray();
        expectedTimestamps.add("2022-02-10T01:51:19Z");
        expectedTimestamps.add("2022-02-09T17:46:07Z");
        expectedTimestamps.add("2022-02-09T17:45:36Z");
        expectedTimestamps.add("2022-02-07T20:50:19Z");

        JSONArray results = parser.parseTimestamps(testStream);
        Assertions.assertEquals(expectedTimestamps, results);
    }


    @Test
    public void testParseUsers() throws IOException {
        JSONArray expectedUsers = new JSONArray();
        expectedUsers.add("G\\u00fcnniX");
        expectedUsers.add("Jessicapierce");
        expectedUsers.add("Finnusertop");
        expectedUsers.add("Ganmatthew");

        JSONArray result = parser.parseUsers(soupStream);
        Assertions.assertEquals(expectedUsers, result);
    }

    @Test
    public void testParseMissing() throws IOException {
        String result = parser.parseMissing(invalidStream);
        Assertions.assertEquals("", result);
    }

    @Test
    public void testParseRedirect() throws IOException {
        String result = parser.parseRedirect(testStream);
        Assertions.assertEquals("Joe Biden", result);
    }
}
