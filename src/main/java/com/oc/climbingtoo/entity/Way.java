package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Way {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private Integer height;

    @Getter @Setter
    private Integer spit;

    @Getter @Setter
    private String climbingRating;

    @Getter @Setter
    private Date createdAt;

    @Getter @Setter
    private Date updatedAt;
}
