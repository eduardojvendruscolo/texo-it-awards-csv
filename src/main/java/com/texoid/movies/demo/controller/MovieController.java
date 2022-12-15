package com.texoid.movies.demo.controller;

import com.texoid.movies.demo.domain.AwardsIntervals;
import com.texoid.movies.demo.dto.IntervalMinMaxDTO;
import com.texoid.movies.demo.dto.ProducerIntervalDTO;
import com.texoid.movies.demo.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/min-max")
    public ResponseEntity<IntervalMinMaxDTO> getMinMaxAwardInterval(){

        IntervalMinMaxDTO returnDto = new IntervalMinMaxDTO();

        AwardsIntervals minAwardPeriod = movieService.findMinPeriodAwardProducer();

        ProducerIntervalDTO pi1 = new ProducerIntervalDTO();
        pi1.setInterval(minAwardPeriod.getYearsInterval());
        pi1.setProducer(minAwardPeriod.getProducer().getName());
        pi1.setPreviousWin(minAwardPeriod.getPreviousWin());
        pi1.setFollowingWin(minAwardPeriod.getFollowingWin());

        List<ProducerIntervalDTO> list1 = new ArrayList<>();
        list1.add(pi1);

        AwardsIntervals maxAwardPeriod = movieService.findMaxPeriodAwardProducer();

        ProducerIntervalDTO pi2 = new ProducerIntervalDTO();
        pi2.setInterval(maxAwardPeriod.getYearsInterval());
        pi2.setProducer(maxAwardPeriod.getProducer().getName());
        pi2.setPreviousWin(maxAwardPeriod.getPreviousWin());
        pi2.setFollowingWin(maxAwardPeriod.getFollowingWin());

        List<ProducerIntervalDTO> list2 = new ArrayList<>();
        list2.add(pi2);

        returnDto.setMin(list1);
        returnDto.setMax(list2);

        return new ResponseEntity<>(returnDto, HttpStatus.OK);
    }

}
