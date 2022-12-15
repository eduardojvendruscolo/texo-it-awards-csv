package com.texoid.movies.demo.csv;

import com.texoid.movies.demo.domain.Movie;
import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(1)
@Getter
public class CsvFileReader implements CommandLineRunner {

    private List<Movie> movies = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {

        Path path = Path.of("src/main/resources/movielist.csv");
        List<String> lines = Files.readAllLines(path).stream().skip(1).collect(Collectors.toList());

        for (String line: lines) {

            String[] attributes = line.split(";");

            Integer year = Integer.valueOf(attributes[MovieConstants.YEAR.getIndex()]);
            String title = attributes[MovieConstants.TITLE.getIndex()];
            String studios = attributes[MovieConstants.STUDIOS.getIndex()];
            String producer = attributes[MovieConstants.PRODUCER.getIndex()];

            Boolean winner = false;
            if (attributes.length == 5)
                winner = attributes[MovieConstants.WINNER.getIndex()].compareTo("yes") == 0;

            Movie movie = new Movie(year, title, studios, producer, winner);
            movies.add(movie);

        }

    }
}
