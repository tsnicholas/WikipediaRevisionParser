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

    public int parsePageNum(InputStream wikiRevision) {
        return -1;
    }

    // TODO: add parseRedirect(), this will be used to check if the user was redirected
}
