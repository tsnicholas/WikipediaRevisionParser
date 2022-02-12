package edu.bsu.cs222.model;

public record RevisionData(String[] timestamps, String[] usernames, String redirectedInfo) {
    public String toString() {
        StringBuilder organizedData = new StringBuilder();
        organizedData.append(redirectedInfo);
        for(int i = 0; i < timestamps.length; i++) {
            organizedData.append(usernames[i]);
            organizedData.append(" ");
            organizedData.append(timestamps[i]);
            organizedData.append("\n");
        }
        return organizedData.toString();
    }
}