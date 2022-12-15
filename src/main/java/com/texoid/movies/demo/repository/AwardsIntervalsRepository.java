package com.texoid.movies.demo.repository;

import com.texoid.movies.demo.domain.AwardsIntervals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AwardsIntervalsRepository extends JpaRepository<AwardsIntervals, UUID> {
}
