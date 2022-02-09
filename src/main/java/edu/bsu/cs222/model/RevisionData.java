package edu.bsu.cs222.model;

import java.io.InputStream;
import java.util.HashMap;

public class RevisionData {
    private HashMap<String, String> revisionList;
    private InputStream data;

    public RevisionData(InputStream data) {
        revisionList = new HashMap<>();
        this.data = data;
        retrieveData();
    }

    private void retrieveData() {

    }
}
