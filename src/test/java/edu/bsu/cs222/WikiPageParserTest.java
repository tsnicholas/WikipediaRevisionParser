package edu.bsu.cs222;

import edu.bsu.cs222.model.WikiPageParser;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.HashMap;

public class WikiPageParserTest {

    @Test
    public void testParse() {
        HashMap<String, String> result = new HashMap<>();
        HashMap<String, String> expected = new HashMap<>();
        expected.put("2021-12-04T18:29:33Z", "GünniX");

        WikiPageParser parser = new WikiPageParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
    }
}
