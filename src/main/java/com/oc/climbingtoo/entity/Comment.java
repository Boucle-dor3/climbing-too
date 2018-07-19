package com.oc.climbingtoo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


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

    @ManyToOne
    @JoinColumn(name= "fk_comment_id_parent")
    @Getter @Setter
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @Getter
    private List<Comment> children = new ArrayList<>();
}
