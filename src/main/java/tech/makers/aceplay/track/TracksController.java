package tech.makers.aceplay.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import tech.makers.aceplay.TokenDecoder;
import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import static org.springframework.http.HttpStatus.NOT_FOUND;

// https://www.youtube.com/watch?v=5r3QU09v7ig&t=2410s
@RestController
public class TracksController {
  @Autowired private TrackRepository trackRepository;
  @Autowired private UserRepository userRepository;
  @GetMapping("/api/tracks")
  public Iterable<Track> index(@RequestHeader("authorization") String token) {
    String username = TokenDecoder.getUsernameFromToken(token);
    User user = userRepository.findByUsername(username);
    return trackRepository.findAllByUser(user);
  }

  @PostMapping("/api/tracks" )
  public Track create(@RequestBody TrackDTO trackDTO,@RequestHeader("authorization") String token) {
    String username = TokenDecoder.getUsernameFromToken(token);
    User user = userRepository.findByUsername(username);
    Track track = new Track(trackDTO.getTitle(),trackDTO.getArtist(), trackDTO.getPublicUrl());
    track.setUser(user);
    return trackRepository.save(track);
  }

  @PatchMapping("/api/tracks/{id}")
  public Track update(@PathVariable Long id, @RequestBody TrackDTO newTrack) {
    Track track = trackRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + id));
    track.setTitle(newTrack.getTitle());
    track.setArtist(newTrack.getArtist());
    trackRepository.save(track);
    return track;
  }

  @DeleteMapping("/api/tracks/{id}")
  public void delete(@PathVariable Long id) {
    Track track = trackRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + id));
    trackRepository.delete(track);
  }
}
