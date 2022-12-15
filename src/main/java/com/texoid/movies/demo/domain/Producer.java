package com.texoid.movies.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "producer")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Producer {

    @Id
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Producer> movies;

    public Producer(String name) {
        this.name = name;
    }

    @PrePersist
    private void setNewId() {
        this.setId(UUID.randomUUID());
    }
}
