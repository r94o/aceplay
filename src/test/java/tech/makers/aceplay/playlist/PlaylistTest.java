package tech.makers.aceplay.playlist;

import org.junit.jupiter.api.Test;
import tech.makers.aceplay.playlist.Playlist;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// https://www.youtube.com/watch?v=L4vkcgRnw2g&t=1099s
class PlaylistTest {
  @Test
  void testConstructs() {
    Playlist subject = new Playlist("Hello, world!", List.of());
    assertEquals("Hello, world!", subject.getName());
    assertEquals(List.of(), subject.getTracks());
    assertEquals(null, subject.getId());
  }

  @Test
  void testToString() {
    Playlist subject = new Playlist("Hello, world!");
    assertEquals(
            "Playlist[id=null name='Hello, world!']",
            subject.toString());
  }
}

