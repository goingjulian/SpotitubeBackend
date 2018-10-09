package nl.korfdegidts.entity;

public class Track implements IEntity {

    private int id;

    private String title;

    private String performer;

    private int duration;

    private int playCount;

    private boolean offlineAvailable;

    private String album;

    private String description;

    private String publication_date;

    public Track() {
    }

    public Track(int id, String title, String performer, int duration,
                 int playCount, boolean offlineAvailable, String album,
                 String description, String publication_date) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playCount = playCount;
        this.offlineAvailable = offlineAvailable;
        this.album = album;
        this.description = description;
        this.publication_date = publication_date;
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

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
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

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
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
