package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class revisionParser {

    public void parse(InputStream inputStream) throws IOException {
        JSONArray username = JsonPath.read(inputStream, "$..user");
        JSONArray timestamp = JsonPath.read(inputStream, "$..timestamp");
    }
}
