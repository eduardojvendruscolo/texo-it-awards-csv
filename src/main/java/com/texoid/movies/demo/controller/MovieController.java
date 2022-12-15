package com.texoid.movies.demo.controller;

import com.texoid.movies.demo.domain.AwardsIntervals;
import com.texoid.movies.demo.dto.IntervalMinMaxDTO;
import com.texoid.movies.demo.dto.AwardsIntervalsDTO;
import com.texoid.movies.demo.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "movies")
public class MovieController {

    private final ModelMapper modelMapper;
    private final MovieService movieService;

    public MovieController(ModelMapper modelMapper, MovieService movieService) {
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(AwardsIntervals.class, AwardsIntervalsDTO.class)
                .addMapping(AwardsIntervals::getProducerName, AwardsIntervalsDTO::setProducer)
                .addMapping(AwardsIntervals::getYearsInterval, AwardsIntervalsDTO::setInterval);

        this.movieService = movieService;
    }

    @GetMapping(path = "/min-max")
    public ResponseEntity<IntervalMinMaxDTO> getMinMaxAwardInterval(){

        IntervalMinMaxDTO returnDto = new IntervalMinMaxDTO();

        List<AwardsIntervals> minAwardPeriodList = movieService.findMinPeriodAwardProducer();
        List<AwardsIntervalsDTO> minAwardsIntervalsDTOList = minAwardPeriodList
                .stream().map(ai -> modelMapper.map(ai, AwardsIntervalsDTO.class)).collect(Collectors.toList());
        returnDto.setMin(minAwardsIntervalsDTOList);

        List<AwardsIntervals> maxAwardPeriodList = movieService.findMaxPeriodAwardProducer();
        List<AwardsIntervalsDTO> maxAwardsIntervalsDTOList = maxAwardPeriodList
                .stream().map(ai -> modelMapper.map(ai, AwardsIntervalsDTO.class)).collect(Collectors.toList());
        returnDto.setMax(maxAwardsIntervalsDTOList);

        return new ResponseEntity<>(returnDto, HttpStatus.OK);
    }

}
