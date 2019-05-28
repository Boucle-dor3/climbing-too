package com.oc.climbingtoo.service;


import com.oc.climbingtoo.entity.Comment;
import com.oc.climbingtoo.repositories.CommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentDAO commentDAO;

    private List<Comment> findParentsBySiteId(Integer idSite) {
        return commentDAO.findParentsBySiteId(idSite);
    }

}
