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

  @Autowired private PlaylistService playlistService;

  @GetMapping("/api/playlists")
  public Iterable<Playlist> playlists() {
    return playlistService.getAllPlaylists();
  }

  @PostMapping("/api/playlists")
  public Playlist create(@RequestBody PlaylistDTO playlistDTO) {
    return playlistService.createPlayList(playlistDTO);
  }

  @GetMapping("/api/playlists/user/{id}")
  public Iterable<Playlist> userPlaylists(@PathVariable Long id) {
    return playlistService.getPlaylistsByUserId(id);
  }

  @PostMapping("/api/playlists/user/{id}")
  public Playlist createWithUser(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) {
   return playlistService.createPlaylistWithUser(id,playlistDTO);
  }

  @GetMapping("/api/playlists/{id}")
  public Playlist get(@PathVariable Long id) {
  return playlistService.getPlaylistById(id);
  }

  @PutMapping("/api/playlists/{id}/tracks")
  public Track addTrack(@PathVariable Long id, @RequestBody TrackIdentifierDto trackIdentifierDto) {
    return playlistService.addTrack(id, trackIdentifierDto);
  }


  @DeleteMapping("/api/playlists/{playlist_id}/tracks/{track_id}" )
  public void delete(@PathVariable Long playlist_id, @PathVariable Long track_id) {
    playlistService.deleteTracksFromPlaylist(playlist_id, track_id);
  }

  @DeleteMapping("/api/playlists/{playlist_id}" )
  public void delete(@PathVariable Long playlist_id) {
  playlistService.deletePlaylists(playlist_id);
  }

}



