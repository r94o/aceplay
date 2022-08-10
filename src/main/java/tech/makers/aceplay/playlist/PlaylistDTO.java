package tech.makers.aceplay.playlist;

import tech.makers.aceplay.track.Track;

import java.util.Set;

public class PlaylistDTO {
    private String name;

    private Set<Track> tracks;

    public PlaylistDTO() {}

    public PlaylistDTO(String name) {
        this(name, null);
    }

    public String getName() {
        return this.name;
    }

    public Set<Track> getTrack() { return this.tracks; }

    public PlaylistDTO(String name, Set<Track> tracks) {
        this.name = name;
        this.tracks = tracks;
    }
}
