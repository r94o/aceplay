package tech.makers.aceplay.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.track.TrackRepository;
import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PlaylistService {
    @Autowired private PlaylistRepository playlistRepository;
    @Autowired private TrackRepository trackRepository;
    @Autowired private UserRepository userRepository;
    public Iterable<Playlist> getAllPlaylists() { return playlistRepository.findAll();
    }
    public Playlist createPlayList(PlaylistDTO playlistDTO){
        Playlist playlist = new Playlist(playlistDTO.getName());
        try {
            return playlistRepository.save(playlist);
        }
        catch(TransactionSystemException e){
            throw new ResponseStatusException(BAD_REQUEST, "Please enter a valid name");
        }
    }

   public Iterable<Playlist> getPlaylistsByUserId(Long id){
       return playlistRepository.findAllByUserId(id);
   }

   public Playlist createPlaylistWithUser(Long id, PlaylistDTO playlistDTO ) {
       Playlist playlist = new Playlist(playlistDTO.getName());
       User user = userRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No user exists with id " + id));
       playlist.setUser(user);
       return playlistRepository.save(playlist);
   }

   public Playlist getPlaylistById(Long id){
       return playlistRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + id));
   }
    public Track addTrack(Long id, TrackIdentifierDto trackIdentifierDto) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + id));
        Track track = trackRepository.findById(trackIdentifierDto.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + trackIdentifierDto.getId()));
        playlist.getTracks().add(track);
        playlistRepository.save(playlist);
        return track;
    }

    public void deleteTracksFromPlaylist(Long playlist_id, Long track_id){
    Playlist playlist = playlistRepository.findById(playlist_id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + playlist_id));
    Track track = trackRepository.findById(track_id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + track_id));
    playlist.getTracks().remove(track);
    playlistRepository.save(playlist);
}

}
