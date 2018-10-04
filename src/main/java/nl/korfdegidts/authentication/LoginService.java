package nl.korfdegidts.authentication;

import nl.korfdegidts.entity.Song;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.entity.Video;
import nl.korfdegidts.exception.UserNotFoundException;

public class LoginService {
    //Replace with db
    private User hardCodedUser = new User(new UserCredentials("julian", "pass"));

    public LoginService() {
        hardCodedUser.addPlaylist(0, "Cool Songs", true);
        hardCodedUser.addPlaylist(1, "Hipster Songs", true);
        hardCodedUser.addPlaylist(2, "Sad Songs", false);
        hardCodedUser.addPlaylist(3, "Great Songs", true);

        Track sunshineSong = new Song(0, "Sunshine", "Pietje Pietersen",
                254, "Songs of shine", 3, true);

        Track moonshineVideo = new Video(0, "Moonshine", "Pietje Pietersen",
                120, 500, "A movie", "02-02-2016", false);

        Track rainVideo = new Video(0, "Rain", "Pietje Pietersen",
                500, 1, "Another movie", "08-08-1991", true);

        hardCodedUser.getPlaylists().get(0).addTrack(sunshineSong);
        hardCodedUser.getPlaylists().get(0).addTrack(moonshineVideo);
        hardCodedUser.getPlaylists().get(2).addTrack(rainVideo);
    }

    public User loginUser(UserCredentials credentials) throws UserNotFoundException {
        User user = getUserFromCredentials(credentials);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    private User getUserFromCredentials(UserCredentials credentials) {
        //replace with db
        if (credentials.getUser().equals(hardCodedUser.getCredentials().getUser())
                && credentials.getPassword().equals(hardCodedUser.getCredentials().getPassword()))
            return hardCodedUser;

        return null;
    }

    public User getUserFromToken(String token) throws UserNotFoundException {
        //Replace with db
        if (hardCodedUser.getTokenObject().getToken().equals(token)) return hardCodedUser;

        throw new UserNotFoundException();
    }
}
