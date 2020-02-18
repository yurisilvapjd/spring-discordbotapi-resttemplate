package com.yurisilvapjd.springdiscordbotapiresttemplate.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public class TrackResponse implements Serializable {

    private boolean success;
    private int length;

    private List<Track> result = new LinkedList<>();
}
