package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Topo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private String topoTitle;

    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String topoDescription;

    @Getter @Setter
    private String topoPicture;

    @Getter @Setter
    private Integer barCode;

    @Getter @Setter
    private Date createdAt;

    @Getter @Setter
    private Date updatedAt;
}
