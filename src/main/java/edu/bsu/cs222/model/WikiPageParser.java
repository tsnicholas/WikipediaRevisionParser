package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class WikiPageParser {
    private HashMap<String, String> data;
    private JSONArray timestamps;
    private JSONArray users;

    public HashMap parse(InputStream testDataStream) throws IOException {
        data = new HashMap<String, String>();
        timestamps = (JSONArray) JsonPath.read(testDataStream, "$..timestamp");
        users = (JSONArray) JsonPath.read(testDataStream, "$..user");

        for(int i = 0; i < timestamps.toArray().length - 1; i++) {
            data.put(timestamps.get(i).toString(), users.get(i).toString());
        }

        return data;
    }
}
