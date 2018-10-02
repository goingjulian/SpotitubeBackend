package nl.korfdegidts.components;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private static int currentId = 0;
    private User ownerObj;

    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks = new ArrayList<>();

    public Playlist(String name, User ownerObj) {
        this.name = name;
        this.ownerObj = ownerObj;

        this.owner = true;

        id = currentId;
        currentId++;
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
}
