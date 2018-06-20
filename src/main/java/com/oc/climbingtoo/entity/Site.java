package com.oc.climbingtoo.entity;

import com.oc.climbingtoo.enumeration.SiteType;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;





@Entity
public class Site {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String picture;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private SiteType type;


}



