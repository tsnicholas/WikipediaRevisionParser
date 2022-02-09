package edu.bsu.cs222;

import edu.bsu.cs222.model.WikiPageParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class WikiPageParserTest {

    @Test
    public void testParse() throws IOException {
        HashMap<String, String> expected = new HashMap<>();
        expected.put("2021-12-04T18:29:33Z", "GünniX");
        expected.put("2021-12-02T19:17:09Z", "Jessicapierce");
        expected.put("2021-12-02T19:09:33Z", "Finnusertop");
        expected.put("2021-10-24T11:34:47Z", "Ganmatthew");

        WikiPageParser parser = new WikiPageParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        HashMap<String, String> result = parser.parse(testDataStream);

        Assertions.assertEquals(expected, result);
    }
}
