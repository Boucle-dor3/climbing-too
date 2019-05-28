package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;



@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(name= "fk_site_id")
    private Site site;

    @ManyToOne
    @JoinColumn(name= "fk_comment_id_parent")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Comment> children = new ArrayList<>();
}
