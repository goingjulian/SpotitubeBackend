package nl.korfdegidts.entity;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;

    private String name;
    private boolean owner;
    private List<Track> tracks = new ArrayList<>();

    public Playlist(int id, String name, boolean owner) {
        this.name = name;
        this.owner = owner;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getOwner() {
        return owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }
}
