package com.texoid.movies.demo.service;

import com.texoid.movies.demo.domain.AwardsIntervals;
import com.texoid.movies.demo.repository.AwardsIntervalsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final AwardsIntervalsRepository awardsIntervalsRepository;

    public MovieService(AwardsIntervalsRepository awardsIntervalsRepository) {
        this.awardsIntervalsRepository = awardsIntervalsRepository;
    }

    public List<AwardsIntervals> findMinPeriodAwardProducer() {
        return awardsIntervalsRepository.getMinAwardsIntervals();
    }

    public List<AwardsIntervals> findMaxPeriodAwardProducer() {
        return awardsIntervalsRepository.getMaxAwardsIntervals();
    }

}
