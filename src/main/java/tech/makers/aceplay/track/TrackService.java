package tech.makers.aceplay.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.ByIdFinder;
import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TrackService {

    @Autowired private TrackRepository trackRepository;
    @Autowired private UserRepository userRepository;

    public Iterable<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Iterable<Track> getTracksByUserId(Long id) {
        return trackRepository.findAllByUserId(id);
    }

    public Track createTrackWithUser(Long user_id, TrackDTO trackDTO) {
        Track track = new Track(trackDTO.getTitle(),trackDTO.getArtist(), trackDTO.getPublicUrl());
        User user = ByIdFinder.findById(userRepository, user_id);
        track.setUser(user);
        return trackRepository.save(track);
    }

    public Track createTrack(TrackDTO trackDTO) {
        Track track = new Track(trackDTO.getTitle(),trackDTO.getArtist(), trackDTO.getPublicUrl());
        try {
            return trackRepository.save(track);}
        catch(TransactionSystemException e) {
            throw new ResponseStatusException(BAD_REQUEST, "Please enter a valid track name and artist name");
        }
    }

    public Track updateTrack(Long track_id, TrackDTO newTrack) {
        Track track = ByIdFinder.findById(trackRepository, track_id);
        track.setTitle(newTrack.getTitle());
        track.setArtist(newTrack.getArtist());
        trackRepository.save(track);
        return track;
    }

    public void deleteTrack(Long track_id) {
        Track track = ByIdFinder.findById(trackRepository, track_id);
        trackRepository.delete(track);
    }

    public Iterable<Track> getSuggestedTracks(Long user_id) {
        // get all playlists
        // get all tracks from playlists
        // count popularity
        //
        //
        //
        // sort by count
        // trim array to max 10
        // return array

    }
}
