package com.texoid.movies.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "awards_intervals")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AwardsIntervals {

    @Id
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @ManyToOne
    private Producer producer;
    private Integer yearsInterval;
    private Integer previousWin;
    private Integer followingWin;

    public AwardsIntervals(Producer producer, Integer yearsInterval, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.yearsInterval = yearsInterval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    @PrePersist
    private void setNewId() {
        this.setId(UUID.randomUUID());
    }

    public String getProducerName(){
        return producer.getName();
    }

}
