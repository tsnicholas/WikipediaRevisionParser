package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikiPageParser {

    public String[] parseTimestamps(InputStream wikiRevisionData) throws IOException {
        JSONArray timestamps = JsonPath.read(wikiRevisionData, "$..timestamp");
        return convertJsonArrayToStringArray(timestamps);
    }

    public String[] parseUsers(InputStream wikiRevisionData) throws IOException {
        JSONArray usernames = JsonPath.read(wikiRevisionData, "$..user");
        return convertJsonArrayToStringArray(usernames);
    }

    public String parseRedirect(InputStream wikiRevisionData) throws IOException {
        JSONArray redirect = JsonPath.read(wikiRevisionData, "$..to");
        return redirect.get(0).toString();
    }

    public boolean parseForPageID(InputStream wikiRevisionData) throws IOException {
        JSONArray pages = JsonPath.read(wikiRevisionData, "$..pages");
        System.out.println(pages.get(0).toString());
        return pages.get(0).toString().contains("pageid");
    }

    private String[] convertJsonArrayToStringArray(JSONArray jsonArray) {
        String[] convertedJsonArray = new String[jsonArray.size()];
        for(int i = 0; i < convertedJsonArray.length; i++) {
            convertedJsonArray[i] = jsonArray.get(i).toString();
        }
        return convertedJsonArray;
    }
}
