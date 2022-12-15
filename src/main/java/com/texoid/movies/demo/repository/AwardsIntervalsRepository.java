package com.texoid.movies.demo.repository;

import com.texoid.movies.demo.domain.AwardsIntervals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AwardsIntervalsRepository extends JpaRepository<AwardsIntervals, UUID> {

    @Query(value = "select ai from AwardsIntervals ai where ai.yearsInterval = (select min(ai2.yearsInterval) from AwardsIntervals ai2)")
    List<AwardsIntervals> getMinAwardsIntervals();

    @Query(value = "select ai from AwardsIntervals ai where ai.yearsInterval = (select max(ai2.yearsInterval) from AwardsIntervals ai2)")
    List<AwardsIntervals> getMaxAwardsIntervals();

}
