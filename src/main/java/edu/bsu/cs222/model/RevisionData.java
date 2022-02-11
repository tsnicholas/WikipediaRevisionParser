package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class RevisionData {
    private final HashMap<String, String> revisionList;
    private final InputStream data;
    private JSONArray timestamps;
    private JSONArray usernames;


    public RevisionData(InputStream data) {
        revisionList = new HashMap<>();
        this.data = data;
        retrieveData();
        transferData();
    }

    private void retrieveData() {
        WikiPageParser parser = new WikiPageParser();

        try {
            timestamps = parser.parseTimestamps(data);
            usernames = parser.parseUsers(data);
        }
        catch(IOException e) {
            e.getCause();
        }
    }

    private void transferData() {
        for (int i = 0; i <= timestamps.size(); i++) {
            String k = timestamps.get(i).toString();
            String v = usernames.get(i).toString();
            revisionList.put(k, v);
        }
    }

    //TODO: Add a toString() method for RevisionData, this will be the results that's displayed. Recommend using a StringBuilder
    public String toString() {
        return "";
    }


    //TODO: Should add an exception that checks if the wikiPage exists, of course this needs to be in it's own seperate class

}
