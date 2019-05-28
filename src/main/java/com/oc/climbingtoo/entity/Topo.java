package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
public class Topo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String topoTitle;

    @Column(columnDefinition="TEXT")
    private String topoDescription;

    private String topoPicture;

    private Integer barCode;

    private Date createdAt;

    private Date updatedAt;
}
