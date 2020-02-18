package com.yurisilvapjd.springdiscordbotapiresttemplate.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Track implements Serializable {

    private String track;
    private int id_track;
    private String haslyrics;
    private String artist;
    private int id_artist;
    private String album;
    private String lyrics;
    private int id_album;
    private String cover;
    private String api_artist;
    private String api_albums;
    private String api_album;
    private String api_tracks;
    private String api_track;
    private String api_lyrics;
    private String copyright_label;
    private String copyright_notice;
    private String copyright_text;
}
