package tech.makers.aceplay.track;

import org.springframework.data.repository.CrudRepository;
import tech.makers.aceplay.playlist.Playlist;

// https://www.youtube.com/watch?v=5r3QU09v7ig&t=2856s
public interface TrackRepository extends CrudRepository<Track, Long> {
  Track findFirstByOrderByIdAsc();
  Iterable<Track> findAllByUserId(Long id);

  Iterable<Track> findAllNotContainingUserId(long user_id);
}
