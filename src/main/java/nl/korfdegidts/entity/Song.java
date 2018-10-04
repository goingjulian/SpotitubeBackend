package nl.korfdegidts.entity;

public class Song extends Track {
    private String album;

    public Song(int id, String title, String performer, int duration, String album, int playcount, boolean offlineAvailable) {
        super(id, title, performer, duration, playcount, offlineAvailable);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }
}
