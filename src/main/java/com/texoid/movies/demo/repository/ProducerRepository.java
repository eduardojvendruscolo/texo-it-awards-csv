package com.texoid.movies.demo.repository;

import com.texoid.movies.demo.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProducerRepository extends JpaRepository<Producer, UUID> {
    @Query(value = "Select p from Producer p where p.name = :producerName")
    Producer findProducerByName(String producerName);
}
