package com.yurisilvapjd.springdiscordbotapiresttemplate.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrackResponseWithLyrics {

    private boolean success;
    private int length;

    private Track result;
}
