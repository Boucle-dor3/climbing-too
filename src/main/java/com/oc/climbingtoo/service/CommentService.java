package com.oc.climbingtoo.service;


import com.oc.climbingtoo.entity.Comment;
import com.oc.climbingtoo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private List<Comment> findParentsBySiteId(Integer idSite) {
        return commentRepository.findParentsBySiteId(idSite);
    }

}
