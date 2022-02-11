package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RevisionData {
    private final InputStream data;
    private Object[] timestamps;
    private Object[] usernames;


    public RevisionData(InputStream data) throws IOException {
        this.data = data;
        retrieveData();
    }

    private void retrieveData() throws IOException {
        WikiPageParser parser = new WikiPageParser();

        timestamps = parser.parseTimestamps(data);
        usernames = parser.parseUsers(data);
    }

    public String toString() {
        StringBuilder organizedData = new StringBuilder();

        for(int i = 0; i < timestamps.length - 1; i++) {
            organizedData.append(usernames[i]);
            organizedData.append(" ");
            organizedData.append(timestamps[i]);
            organizedData.append("\n");
        }

        return organizedData.toString();
    }
}
