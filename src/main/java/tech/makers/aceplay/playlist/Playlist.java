package tech.makers.aceplay.playlist;

import com.fasterxml.jackson.annotation.JsonGetter;
import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

// https://www.youtube.com/watch?v=vreyOZxdb5Y&t=448s
@Entity
public class Playlist {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotBlank
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Track> tracks;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="user_id")
  private User user;

  public Playlist() {}

  public Playlist(String name) {
    this(name, null);
  }

  public Playlist(String name, List<Track> tracks) {
    this.name = name;
    this.tracks = tracks;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @JsonGetter("tracks")
  public List<Track> getTracks() {
    if (null == tracks) {
      return List.of();
    }
    return tracks;
  }



  @Override
  public String toString() {
    return String.format("Playlist[id=%d name='%s']", id, name);
  }
}
