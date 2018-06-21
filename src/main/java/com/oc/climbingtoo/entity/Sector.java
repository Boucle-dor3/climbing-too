package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Sector {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private Date createdAt;

    @Getter @Setter
    private Date updatedAt;
}
