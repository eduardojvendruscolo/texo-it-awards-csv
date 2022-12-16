package com.texoid.movies.demo.csv;

import com.texoid.movies.demo.domain.Movie;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(1)
@Getter
public class CsvFileReader implements CommandLineRunner {
    private List<Movie> movies = new ArrayList<>();
    @Value("${csv.file.url}")
    private String csvFileUrl;

    @Override
    public void run(String... args) throws Exception {

        Path path = Path.of(csvFileUrl);

        List<String> lines = Files.readAllLines(path).stream().skip(1).collect(Collectors.toList());

        for (String line: lines) {

            String[] attributes = line.split(";");

            Integer year = Integer.valueOf(attributes[MoviesAwardsComlumnEnum.YEAR.getIndex()]);
            String title = attributes[MoviesAwardsComlumnEnum.TITLE.getIndex()];
            String studios = attributes[MoviesAwardsComlumnEnum.STUDIOS.getIndex()];
            String producer = attributes[MoviesAwardsComlumnEnum.PRODUCER.getIndex()];

            Boolean winner = false;
            if (attributes.length == 5)
                winner = attributes[MoviesAwardsComlumnEnum.WINNER.getIndex()].compareTo("yes") == 0;

            Movie movie = new Movie(year, title, studios, producer, winner);
            movies.add(movie);

        }

    }
}
