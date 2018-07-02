package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private String countryName;

    public Country (Integer id, String countryName) {
        super();
        this.id = id;
        this.countryName = countryName;
    }
}
