package tech.makers.aceplay.track;

import java.net.URL;

public class TrackDTO {
    private String title;

    private String artist;

    private URL publicUrl;

    public TrackDTO(String title, String artist, URL publicUrl) {
        this.title = title;
        this.artist = artist;
        this.publicUrl = publicUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public URL getPublicUrl() {
        return publicUrl;
    }


}
