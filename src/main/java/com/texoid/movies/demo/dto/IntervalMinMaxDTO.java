package com.texoid.movies.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IntervalMinMaxDTO {
    private List<ProducerIntervalDTO> min;
    private List<ProducerIntervalDTO> max;
}
