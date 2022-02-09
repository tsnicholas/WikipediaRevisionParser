package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class wikiParser {
    public void parseRevision(InputStream inputStream) throws IOException {
        JSONArray timestamp = JsonPath.read(inputStream, "$..timestamp");
        JSONArray username = JsonPath.read(inputStream, "$..username");
    }
}
