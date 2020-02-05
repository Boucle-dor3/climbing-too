package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String departmentName;

    @ManyToOne
    @JoinColumn(name= "fk_country_id")
    private Country country;
}
