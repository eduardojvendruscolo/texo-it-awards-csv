package com.texoid.movies.demo.service;

import com.texoid.movies.demo.domain.AwardsIntervals;
import com.texoid.movies.demo.repository.AwardsIntervalsRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class MovieService {

    private AwardsIntervalsRepository awardsIntervalsRepository;

    public MovieService(AwardsIntervalsRepository awardsIntervalsRepository) {
        this.awardsIntervalsRepository = awardsIntervalsRepository;
    }

    public AwardsIntervals findMinPeriodAwardProducer() {

        AwardsIntervals minAwardInterval = awardsIntervalsRepository.findAll().stream().sorted(
                Comparator.comparing(AwardsIntervals::getYearsInterval)).findFirst().get();

        return minAwardInterval;
    }

    public AwardsIntervals findMaxPeriodAwardProducer() {

        AwardsIntervals maxAwardInterval = awardsIntervalsRepository.findAll().stream().sorted(
                (o1, o2) -> o2.getYearsInterval().compareTo(o1.getYearsInterval())).findFirst().get();

        return maxAwardInterval;
    }

}
