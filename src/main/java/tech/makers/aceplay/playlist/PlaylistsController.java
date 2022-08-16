package tech.makers.aceplay.playlist;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.track.TrackRepository;
import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.NOT_FOUND;

// https://www.youtube.com/watch?v=vreyOZxdb5Y&t=0s
@RestController
public class PlaylistsController {
  @Autowired private PlaylistRepository playlistRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private TrackRepository trackRepository;

  @GetMapping("/api/playlists")
  public Iterable<Playlist> playlists() {
    return playlistRepository.findAll();
  }

  @PostMapping("/api/playlists")
  public Playlist create(@RequestBody PlaylistDTO playlistDTO) {
    Playlist playlist = new Playlist(playlistDTO.getName());
    return playlistRepository.save(playlist);
  }

  @GetMapping("/api/playlists/user/{id}")
  public Iterable<Playlist> userPlaylists(@PathVariable Long id) {
    return playlistRepository.findAllByUserId(id);
  }

  @PostMapping("/api/playlists/user/{id}")
  public Playlist createWithUser(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) {
    try {
      Playlist playlist = new Playlist(playlistDTO.getName());
    } catch (IllegalArgumentException e) {
      System.out.println("Please add name");
    }

    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No user exists with id " + id));
    playlist.setUser(user);
    return playlistRepository.save(playlist);
  }

  @GetMapping("/api/playlists/{id}")
  public Playlist get(@PathVariable Long id) {
    return playlistRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + id));
  }

  @PutMapping("/api/playlists/{id}/tracks")
  public Track addTrack(@PathVariable Long id, @RequestBody TrackIdentifierDto trackIdentifierDto) {
    Playlist playlist = playlistRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + id));
    Track track = trackRepository.findById(trackIdentifierDto.getId())
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + trackIdentifierDto.getId()));
    playlist.getTracks().add(track);
    playlistRepository.save(playlist);
    return track;
  }
}
