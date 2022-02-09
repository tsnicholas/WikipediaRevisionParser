package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikiPageParser {

    public JSONArray parseTimestamps(InputStream testDataStream) throws IOException {
        return JsonPath.read(testDataStream, "$..timestamp");
    }

    public JSONArray parseUsers(InputStream wikiRevisionData) throws IOException {
        return JsonPath.read(wikiRevisionData, "$..user");
    }
}
