package com.oc.climbingtoo.entity;

import com.oc.climbingtoo.enumeration.SiteType;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Site {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private String siteName;

    @Column(name="DESC", columnDefinition="TEXT")
    @Getter @Setter
    private String description;

    @Getter @Setter
    private String picture;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private SiteType type;

    @Getter @Setter
    private String region;

    @Getter @Setter
    private String department;


    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String rockClimbing;

    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String accessApproach;

    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String hostingRefueling;

    @Getter @Setter
    private Date createdAt;

    @Getter @Setter
    private Date updatedAt;

}



