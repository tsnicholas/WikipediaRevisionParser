package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikiPageParser {

    public JSONArray parseTimestamps(InputStream wikiRevisionData) throws IOException {
        return JsonPath.read(wikiRevisionData, "$..timestamp");
    }

    public JSONArray parseUsers(InputStream wikiRevisionData) throws IOException {
        return JsonPath.read(wikiRevisionData, "$..user");
    }

    public String parseRedirect(InputStream wikiRevisionData) throws IOException {
        JSONArray Joe = JsonPath.read(wikiRevisionData, "$..to");
        return Joe.get(0).toString();
    }

    public String parseMissing(InputStream wikiRevisionData) throws IOException {
        JSONArray jason = JsonPath.read(wikiRevisionData, "$..missing");
        return jason.get(0).toString();
    }
}
