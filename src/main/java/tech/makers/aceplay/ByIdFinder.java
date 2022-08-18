package tech.makers.aceplay;

import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.playlist.Playlist;
import tech.makers.aceplay.playlist.PlaylistRepository;
import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.track.TrackRepository;
import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ByIdFinder {

    public static Playlist findById(PlaylistRepository repository, Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + id));
    }

    public static Track findById(TrackRepository repository, Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + id));
    }

    public static User findById(UserRepository repository, Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No user exists with id " + id));
    }


}
