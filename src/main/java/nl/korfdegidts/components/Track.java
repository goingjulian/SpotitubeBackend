package nl.korfdegidts.components;

public abstract class Track {
    private String performer;
    private String title;
    private String url;
    private int duration;

    private boolean offlineAvailable;

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }
}
