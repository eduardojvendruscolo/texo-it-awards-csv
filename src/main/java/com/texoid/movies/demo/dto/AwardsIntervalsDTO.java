package com.texoid.movies.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwardsIntervalsDTO {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
