package tech.makers.aceplay.track;

import tech.makers.aceplay.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.net.MalformedURLException;
import java.net.URL;

// https://www.youtube.com/watch?v=5r3QU09v7ig&t=2999s
@Entity
public class Track {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotBlank
  private String title;
  @NotBlank
  private String artist;

  private URL publicUrl;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  public Track() { }

  public Track(String title, String artist, URL publicUrl) {
    this.title = title;
    this.artist = artist;
    this.publicUrl = publicUrl;
  }

  public Track(String title, String artist, String publicUrl) throws MalformedURLException {
    this(title, artist, new URL(publicUrl));
  }

  public String toString() {
    return String.format(
        "Track[id=%d title='%s' artist='%s' publicUrl='%s']", id, title, artist, publicUrl);
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public URL getPublicUrl() {
    return publicUrl;
  }

  public void setPublicUrl(String publicUrl) throws MalformedURLException {
    this.publicUrl = new URL(publicUrl);
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setPublicUrl(URL publicUrl) {
    this.publicUrl = publicUrl;
  }
}
