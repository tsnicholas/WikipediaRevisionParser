package edu.bsu.cs222;

import edu.bsu.cs222.model.WikiPageParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WikiPageParserTest {
    private final WikiPageParser parser = new WikiPageParser();
    private final String testStream = parser.convertInputStreamIntoString(Thread.currentThread().getContextClassLoader().getResourceAsStream("testRedirectedPage.json"));
    private final String invalidStream = parser.convertInputStreamIntoString(Thread.currentThread().getContextClassLoader().getResourceAsStream("testInvalidPage.json"));

    @Test
    public void testParseTimestamps() {
        String[] expected = {"Harami2000", "Surtsicna", "InternetArchiveBot", "AlsoWukai"};
        String[] result = parser.parseUsernames(testStream);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseUsernames() {
        String[] expected = {"2022-02-10T01:51:19Z", "2022-02-09T17:46:07Z", "2022-02-09T17:45:36Z", "2022-02-07T20:50:19Z"};
        String[] result = parser.parseTimestamps(testStream);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testParseRedirect() {
        Assertions.assertEquals("Joe Biden", parser.parseRedirect(testStream));
    }

    @Test
    public void testParseNoRedirect() {
        Assertions.assertEquals("", parser.parseRedirect(invalidStream));
    }

    @Test
    public void testParseForPageIDTrueCase() {
        Assertions.assertTrue(parser.parseForPageID(testStream));
    }

    @Test
    public void testParseForPageIDFalseCase() {
        Assertions.assertFalse(parser.parseForPageID(invalidStream));
    }
}
