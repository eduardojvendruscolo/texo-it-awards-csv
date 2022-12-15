package com.texoid.movies.demo.domain.taks;

import com.texoid.movies.demo.csv.CsvFileReader;
import com.texoid.movies.demo.domain.AwardsIntervals;
import com.texoid.movies.demo.domain.Movie;
import com.texoid.movies.demo.domain.Producer;
import com.texoid.movies.demo.repository.AwardsIntervalsRepository;
import com.texoid.movies.demo.repository.MovieRepository;
import com.texoid.movies.demo.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(2)
public class BatchPersist implements CommandLineRunner {

    @Autowired
    private CsvFileReader csvFileReader;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private AwardsIntervalsRepository awardsIntervalsRepository;

    @Override
    public void run(String... args) throws Exception {

        movieRepository.saveAll(csvFileReader.getMovies());

        Set<String> producerNames = new HashSet<>();
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            String[] producers = movie.getProducersText().split(",| and ");
            for (String producerName : producers){
                if (producerName.trim().compareTo("") != 0)
                    producerNames.add(producerName.trim());
            }
        }

        producerNames.stream().forEach( producerName -> producerRepository.save(new Producer(producerName)) );

        for (Movie movie : movies) {
            String[] producers = movie.getProducersText().split(",| and ");
            for (String producerName : producers) {
                if (producerName.trim().compareTo("") != 0){
                    Producer producer = producerRepository.findProducerByName(producerName.trim());
                    movie.addProducer(producer);
                }
            }
            movieRepository.save(movie);
        }

        List<Producer> producers = producerRepository.findAll();
        for (Producer producer : producers) {
            List<Movie> moviesAwardedByProducer = movieRepository.moviesAwardedByProducer(producer.getId());

            Integer awardsProducerCountLeft = moviesAwardedByProducer.size();
            Integer awardIntervalIndex = 0;

            while (awardsProducerCountLeft >= 2) {
                Movie firstMovie = moviesAwardedByProducer.get(awardIntervalIndex);
                Movie secondMovie = moviesAwardedByProducer.get(++awardIntervalIndex);
                awardIntervalIndex++;

                Integer interval = secondMovie.getMovieYear() - firstMovie.getMovieYear();
                AwardsIntervals awardsIntervals = new AwardsIntervals(producer, interval, firstMovie.getMovieYear(), secondMovie.getMovieYear());
                awardsIntervalsRepository.save(awardsIntervals);

                awardsProducerCountLeft -= 2;
            }
        }

    }
}
