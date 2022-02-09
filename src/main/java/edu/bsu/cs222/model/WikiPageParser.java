package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikiPageParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray timestamp = (JSONArray) JsonPath.read(testDataStream, "$..timestamp");
        JSONArray user = (JSONArray) JsonPath.read(testDataStream, "$..user");
        return timestamp.get(0).toString();
    }
}
