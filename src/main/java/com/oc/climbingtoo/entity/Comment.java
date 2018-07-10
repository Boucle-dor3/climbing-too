package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String message;

    @ManyToOne
    @JoinColumn(name= "fk_site_id")
    @Getter @Setter
    private Site site;

}
