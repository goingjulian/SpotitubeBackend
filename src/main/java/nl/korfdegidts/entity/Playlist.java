package nl.korfdegidts.entity;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements IEntity {
    private int id;

    private String name;

    private boolean owner;

    private List<Track> tracks = new ArrayList<>();

    public Playlist() {
    }

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

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
