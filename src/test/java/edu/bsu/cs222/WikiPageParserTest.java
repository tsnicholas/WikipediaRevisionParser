package edu.bsu.cs222;

import edu.bsu.cs222.model.WikiPageParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

public class WikiPageParserTest {
    private final InputStream testStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testRedirectedPage.json");
    private final InputStream invalidStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testInvalidPage.json");
    private final WikiPageParser parser = new WikiPageParser();

    @Test
    public void testParseTimestamps() throws IOException {
        String[] expected = {"2022-02-10T01:51:19Z", "2022-02-09T17:46:07Z", "2022-02-09T17:45:36Z", "2022-02-07T20:50:19Z"};
        String[] result = parser.parseTimestamps(testStream);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseUsers() throws IOException {
        String[] expected = {"Harami2000", "Surtsicna", "InternetArchiveBot", "AlsoWukai"};
        String[] result = parser.parseUsers(testStream);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseMissingTrueCase() throws IOException {
        Assertions.assertTrue(parser.parseMissing(invalidStream));
    }

    @Test
    public void testParseMissingFalseCase() throws IOException {
        Assertions.assertFalse(parser.parseMissing(testStream));
    }

    @Test
    public void testParseRedirect() throws IOException {
        String result = parser.parseRedirect(testStream);
        Assertions.assertEquals("Joe Biden", result);
    }
}
