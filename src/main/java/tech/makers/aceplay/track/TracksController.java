package tech.makers.aceplay.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

// https://www.youtube.com/watch?v=5r3QU09v7ig&t=2410s
@RestController
public class TracksController {

  @Autowired TrackService trackService;

  @GetMapping("/api/tracks")
  public Iterable<Track> index() {
    return trackService.getAllTracks();
  }

  @GetMapping("/api/tracks/user/{id}")
  public Iterable<Track> userTracks(@PathVariable Long id) {
    return trackService.getTracksByUserId(id);
  }

  @PostMapping("/api/tracks/user/{id}")
  public Track createWithUser(@PathVariable Long id, @RequestBody TrackDTO trackDTO) {
    return trackService.createTrackWithUser(id, trackDTO);
  }

  @PostMapping("/api/tracks")
  public Track create(@RequestBody TrackDTO trackDTO) {
    return trackService.createTrack(trackDTO);
  }

  @PatchMapping("/api/tracks/{id}")
  public Track update(@PathVariable Long id, @RequestBody TrackDTO newTrack) {
    return trackService.updateTrack(id, newTrack);
  }

  @DeleteMapping("/api/tracks/{id}")
  public void delete(@PathVariable Long id) {
    trackService.deleteTrack(id);

  }
}
