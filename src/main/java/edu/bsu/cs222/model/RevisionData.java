package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class RevisionData {
    private final HashMap<String, String> revisionList;
    private final InputStream data;
    private JSONArray timestamps = new JSONArray();
    private JSONArray usernames = new JSONArray();


    public RevisionData(URLConnection connection) throws IOException {
        revisionList = new HashMap<>();
        data = connection.getInputStream();
        retrieveData();
        transferData();
    }

    private void retrieveData() throws IOException {
        WikiPageParser parser = new WikiPageParser();

        timestamps = parser.parseTimestamps(data);
        usernames = parser.parseUsers(data);
    }

    private void transferData() {
        for (int i = 0; i <= timestamps.size(); i++) {
            String k = timestamps.get(i).toString();
            String v = usernames.get(i).toString();
            revisionList.put(k, v);
        }
    }

    public String toString() {
        StringBuilder organizedData = new StringBuilder();

        for(Map.Entry<String, String> revision: revisionList.entrySet()) {
            organizedData.append(revision.getValue());
            organizedData.append(" ");
            organizedData.append(revision.getKey());
            organizedData.append("\n");
        }

        return organizedData.toString();
    }
}
