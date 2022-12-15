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

        //imprimie todos
        //movieRepository.findAll().stream().forEach(System.out::println);

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
            List<Movie> moviesJoelSilver = movieRepository.moviesAwardedByProducer(producer.getId());
            if (moviesJoelSilver.stream().count() >= 2) {
                moviesJoelSilver.stream().forEach(System.out::println);

                Movie firstMovie = moviesJoelSilver.get(0);
                Movie secondMovie = moviesJoelSilver.get(1);
                Integer interval = secondMovie.getMovieYear() - firstMovie.getMovieYear();
                AwardsIntervals awardsIntervals = new AwardsIntervals(producer, interval, firstMovie.getMovieYear(), secondMovie.getMovieYear());
                awardsIntervalsRepository.save(awardsIntervals);
            }
        }

    }
}
