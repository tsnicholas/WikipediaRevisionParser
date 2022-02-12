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

    public String getRedirectInfo() {
        String redirectedName = parser.parseRedirect(wikiRevisionData);
        if(redirectedName.equals("")) {
            return redirectedName;
        }
        return "Redirected to " + redirectedName + "\n";
    }

    public String getUsernames() throws PageDoesNotExistException {
        if(pageExists()) {
            return organizedData(usernames);
        }
        else {
            throw new PageDoesNotExistException();
        }
    }

    public String getTimestamps() throws PageDoesNotExistException {
        if(pageExists()) {
            return organizedData(timestamps);
        }
        else {
            throw new PageDoesNotExistException();
        }
    }

    private String organizedData(String[] insertedData) {
        StringBuilder organizedString = new StringBuilder();
        for(String insertedDatum : insertedData) {
            organizedString.append(insertedDatum);
            organizedString.append("\n");
        }
        return organizedString.toString();
    }

}