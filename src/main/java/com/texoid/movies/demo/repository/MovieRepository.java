package com.texoid.movies.demo.repository;

import com.texoid.movies.demo.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    @Query(value = "select m from Movie m join m.producers p where p.id = :producerId and m.winner = true order by m.movieYear")
    List<Movie> moviesAwardedByProducer(UUID producerId);
}
