package edu.bsu.cs222.model;

import java.io.InputStream;

public class RevisionData {
    private final WikiPageParser parser = new WikiPageParser();
    private final String wikiRevisionData;
    private String[] usernames;
    private String[] timestamps;

    public RevisionData(InputStream revisionData) {
        wikiRevisionData = parser.convertInputStreamIntoString(revisionData);
        if(pageExists()) {
            getWikiRevisionData();
        }
    }

    private boolean pageExists() {
        return parser.parseForPageID(wikiRevisionData);
    }

    private void getWikiRevisionData() {
        usernames = parser.parseUsernames(wikiRevisionData);
        timestamps = parser.parseTimestamps(wikiRevisionData);
    }

    public String toString() {
        if(pageExists()) {
            return organizedData();
        }
        return "Page doesn't exist";
    }

    private String organizedData() {
        StringBuilder organizedString = new StringBuilder();
        organizedString.append(retrieveRedirectInfo());
        for(int i = 0; i < usernames.length; i++) {
            organizedString.append(usernames[i]);
            organizedString.append(" ");
            organizedString.append(timestamps[i]);
            organizedString.append("\n");
        }
        return organizedString.toString();
    }

    private String retrieveRedirectInfo() {
        String redirectedName = parser.parseRedirect(wikiRevisionData);
        if(redirectedName.equals("")) {
           return redirectedName;
        }
        return "Redirected to " + redirectedName + "\n";
    }

}