package com.oc.climbingtoo.entity;

import com.oc.climbingtoo.enumeration.SiteType;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
public class Site {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String siteName;

    @Column(name="DESC", columnDefinition="TEXT")
    private String description;

    private String picture;

    @Enumerated(EnumType.STRING)
    private SiteType type;

    private String region;

    private String department;

    @Column(columnDefinition="TEXT")
    private String rockClimbing;

    @Column(columnDefinition="TEXT")
    private String accessApproach;

    @Column(columnDefinition="TEXT")
    private String hostingRefueling;

    private Date createdAt;

    private Date updatedAt;

}



