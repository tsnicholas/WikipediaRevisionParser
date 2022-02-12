package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;
import java.util.Scanner;

public class WikiPageParser {
    public String convertInputStreamIntoString(InputStream wikiRevisionData) {
        Scanner scanner = new Scanner(wikiRevisionData).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    public String[] parseTimestamps(String testStream) {
        JSONArray timestamps = JsonPath.read(testStream, "$..timestamp");
        return convertJsonArrayToStringArray(timestamps);
    }

    public String[] parseUsernames(String testStream) {
        JSONArray usernames = JsonPath.read(testStream, "$..user");
        return convertJsonArrayToStringArray(usernames);
    }

    public String parseRedirect(String wikiRevisionData) {
        JSONArray redirect = JsonPath.read(wikiRevisionData, "$..to");
        return redirect.get(0).toString();
    }

    public boolean parseForPageID(String wikiRevisionData) {
        JSONArray pages = JsonPath.read(wikiRevisionData, "$..pages");
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
