package com.texoid.movies.demo.csv;

public enum MovieConstants {
    YEAR(0),
    TITLE(1),
    STUDIOS(2),
    PRODUCER(3),
    WINNER(4);
    private final Integer index;
    MovieConstants(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
