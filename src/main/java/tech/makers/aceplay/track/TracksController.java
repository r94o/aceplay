package tech.makers.aceplay.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

// https://www.youtube.com/watch?v=5r3QU09v7ig&t=2410s
@RestController
public class TracksController {
  @Autowired private TrackRepository trackRepository;

  @GetMapping("/api/tracks")
  public Iterable<Track> index() {
    return trackRepository.findAll();
  }

  @GetMapping("/api/tracks/user/{id}")
  public Iterable<Track> userTracks(@PathVariable Long id) {
    return trackRepository.findAllByUserId(id);

  }

  @PostMapping("/api/tracks")
  public Track create(@RequestBody TrackDTO trackDTO) {
    Track track = new Track(trackDTO.getTitle(),trackDTO.getArtist(), trackDTO.getPublicUrl());
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
