package nl.korfdegidts.components;

public class Video extends Track {
    private String publicationDate;
    private String description;

    public Video(int id, String title, String performer,
                 int duration, int playcount, String description,
                 String publicationDate, boolean offlineAvailable) {
        super(id, title, performer, duration, playcount, offlineAvailable);
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }
}
