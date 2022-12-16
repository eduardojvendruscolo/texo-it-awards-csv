package com.texoid.movies.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "movie")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie implements Serializable  {

    @Id
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private Integer movieYear;
    private String title;
    private String studios;
    private String producersText;
    private Boolean winner;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Producer> producers;

    public Movie(Integer movieYear, String title, String studios, String producersText, Boolean winner) {
        this.movieYear = movieYear;
        this.title = title;
        this.studios = studios;
        this.producersText = producersText;
        this.winner = winner;
    }

    public void addProducer(Producer producer){
        producers.add(producer);
    }

    @PrePersist
    private void setNewId() {
        this.setId(UUID.randomUUID());
    }
}
