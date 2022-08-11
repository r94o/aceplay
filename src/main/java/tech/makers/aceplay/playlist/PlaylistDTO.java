package tech.makers.aceplay.playlist;

import tech.makers.aceplay.track.Track;

import java.util.List;
import java.util.Set;

public class PlaylistDTO {
    private String name;

    private List<Track> tracks;

    public PlaylistDTO() {}

    public PlaylistDTO(String name) {
        this(name, null);
    }

    public String getName() {
        return this.name;
    }

    public List<Track> getTrack() { return this.tracks; }

    public PlaylistDTO(String name, List<Track> tracks) {
        this.name = name;
        this.tracks = tracks;
    }
}
