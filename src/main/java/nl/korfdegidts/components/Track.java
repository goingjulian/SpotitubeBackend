package nl.korfdegidts.components;

public abstract class Track {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private int playcount;
    private boolean offlineAvailable;

    public Track(int id, String title, String performer, int duration,
                 int playcount, boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playcount = playcount;
        this.offlineAvailable = offlineAvailable;
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
}
