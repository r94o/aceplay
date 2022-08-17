package tech.makers.aceplay.playlist;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.track.TrackRepository;
import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
    try {
      return playlistRepository.save(playlist);
    }
    catch(TransactionSystemException e){
      throw new ResponseStatusException(BAD_REQUEST, "Please enter a valid name");
    }
  }

  @GetMapping("/api/playlists/user/{id}")
  public Iterable<Playlist> userPlaylists(@PathVariable Long id) {
    return playlistRepository.findAllByUserId(id);
  }

  @PostMapping("/api/playlists/user/{id}")
  public Playlist createWithUser(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) {
      Playlist playlist = new Playlist(playlistDTO.getName());
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

  @DeleteMapping("/api/playlists/{playlist_id}/tracks/{track_id}" )
  public void delete(@PathVariable Long playlist_id, @PathVariable Long track_id) {
    Playlist playlist = playlistRepository.findById(playlist_id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + playlist_id));
    Track track = trackRepository.findById(track_id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + track_id));
    playlist.getTracks().remove(track);
    playlistRepository.save(playlist);
  }

  @DeleteMapping("/api/playlists/{playlist_id}" )
  public void delete(@PathVariable Long playlist_id) {
  try {
    playlistRepository.deleteById(playlist_id) ;
  } catch(EmptyResultDataAccessException e) {
    throw new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + playlist_id);
    }

  }

}



