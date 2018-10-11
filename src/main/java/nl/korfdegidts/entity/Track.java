/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/11/18 9:43 PM
 */

package nl.korfdegidts.entity;

public class Track implements IEntity {

    private int id;

    private String title;

    private String performer;

    private int duration;

    private int playcount;

    private boolean offlineAvailable;

    private String album;

    private String description;

    private String publicationDate;

    public Track() {
    }

    public Track(int id, String title, String performer, int duration,
                 int playcount, boolean offlineAvailable, String album,
                 String description, String publicationDate) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playcount = playcount;
        this.offlineAvailable = offlineAvailable;
        this.album = album;
        this.description = description;
        this.publicationDate = publicationDate;
    }

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
