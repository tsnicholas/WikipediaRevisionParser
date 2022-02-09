package edu.bsu.cs222;

import edu.bsu.cs222.model.WikiPageParser;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


public class WikiPageParserTest {
    private final InputStream testStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");

    @Test
    public void testParseTimestamps() throws IOException {
        JSONArray expectedTimestamps = new JSONArray();
        expectedTimestamps.add("2021-12-04T18:29:33Z");
        expectedTimestamps.add("2021-12-02T19:17:09Z");
        expectedTimestamps.add("2021-12-02T19:09:33Z");
        expectedTimestamps.add("2021-10-24T11:34:47Z");

        WikiPageParser parser = new WikiPageParser();
        JSONArray results = parser.parseTimestamps(testStream);

        Assertions.assertEquals(expectedTimestamps, results);
    }

    @Test
    public void testParseUsers() throws IOException {
        JSONArray expectedUsers = new JSONArray();
        expectedUsers.add("GünniX");
        expectedUsers.add("Jessicapierce");
        expectedUsers.add("Finnusertop");
        expectedUsers.add("Ganmatthew");

        WikiPageParser parser = new WikiPageParser();
        JSONArray results = parser.parseUsers(testStream);

        Assertions.assertEquals(expectedUsers, results);
    }
}
