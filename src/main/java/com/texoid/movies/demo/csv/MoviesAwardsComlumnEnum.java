package com.texoid.movies.demo.csv;

public enum MoviesAwardsComlumnEnum {
    YEAR(0),
    TITLE(1),
    STUDIOS(2),
    PRODUCER(3),
    WINNER(4);
    private final Integer index;
    MoviesAwardsComlumnEnum(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
