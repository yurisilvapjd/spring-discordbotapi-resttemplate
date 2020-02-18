package com.yurisilvapjd.springdiscordbotapiresttemplate.services;

import com.yurisilvapjd.springdiscordbotapiresttemplate.entities.TrackResponse;
import com.yurisilvapjd.springdiscordbotapiresttemplate.entities.TrackResponseWithLyrics;
import com.yurisilvapjd.springdiscordbotapiresttemplate.utils.AuthBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HappiDevMusicService {

    @Autowired
    AuthBox authBox;

    public TrackResponse findTracks(String trackName) {

        String uri = String.format("https://api.happi.dev/v1/music?q=%s&limit=5&apikey=%s&type=track",
                trackName.replace(" ", "%"),
                authBox.getHAPPIDEV_APIKEY());

        TrackResponse trackResponse = new TrackResponse();

        try {
            trackResponse = new RestTemplate().getForObject(uri, TrackResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return trackResponse;
    }

    public TrackResponseWithLyrics getLyrics(String artistId, String albumId, String trackId) {

        String uri = String.format("https://api.happi.dev/v1/music/artists/%s/albums/%s/tracks/%s/lyrics?apikey=%s",
                artistId, albumId, trackId, authBox.getHAPPIDEV_APIKEY());

        TrackResponseWithLyrics trackResponseWithLyrics = new TrackResponseWithLyrics();

        try{
            trackResponseWithLyrics = new RestTemplate().getForObject(uri, TrackResponseWithLyrics.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return trackResponseWithLyrics;
    }
}