package tech.makers.aceplay.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.ByIdFinder;
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

    public Iterable<Playlist> getAllPlaylists() { return playlistRepository.findAll();}

    public Playlist createPlayList(PlaylistDTO playlistDTO){
        Playlist playlist = new Playlist(playlistDTO.getName());
        try {
            return playlistRepository.save(playlist);
        }
        catch(TransactionSystemException e){
            throw new ResponseStatusException(BAD_REQUEST, "Please enter a valid name");
        }
    }

   public Iterable<Playlist> getPlaylistsByUserId(Long user_id){
       return playlistRepository.findAllByUserId(user_id);
   }

   public Playlist createPlaylistWithUser(Long user_id, PlaylistDTO playlistDTO ) {
       Playlist playlist = new Playlist(playlistDTO.getName());
       User user = ByIdFinder.findById(userRepository, user_id);
       playlist.setUser(user);
       return playlistRepository.save(playlist);
   }

   public Playlist getPlaylistById(Long playlist_id){
       return ByIdFinder.findById(playlistRepository, playlist_id);
   }
    public Track addTrack(Long playlist_id, TrackIdentifierDto trackIdentifierDto) {
        Playlist playlist = ByIdFinder.findById(playlistRepository, playlist_id);
        Track track = ByIdFinder.findById(trackRepository, trackIdentifierDto.getId());
        playlist.getTracks().add(track);
        playlistRepository.save(playlist);
        return track;
    }

    public void deleteTracksFromPlaylist(Long playlist_id, Long track_id) {
        Playlist playlist = ByIdFinder.findById(playlistRepository, playlist_id);
        Track track = ByIdFinder.findById(trackRepository, track_id);
        playlist.getTracks().remove(track);
        playlistRepository.save(playlist);
}

    public void deletePlaylists(Long playlist_id) {
        try {
            playlistRepository.deleteById(playlist_id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + playlist_id);
        }
    }


}
