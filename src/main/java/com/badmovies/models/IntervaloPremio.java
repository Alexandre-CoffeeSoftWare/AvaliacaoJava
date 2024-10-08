package com.badmovies.models;

public class IntervaloPremio {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public IntervaloPremio(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public int getInterval() {
        return interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }
}