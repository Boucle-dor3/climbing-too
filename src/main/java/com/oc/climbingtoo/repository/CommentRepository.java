package com.oc.climbingtoo.repository;

import com.oc.climbingtoo.entity.Comment;
import com.oc.climbingtoo.entity.Site;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends org.springframework.data.repository.Repository<Comment, Integer> {
    Comment save (Comment entity);
    List<Comment> findAll();

    Comment findById (Integer idComment);
    List<Comment> findBySite_Id(Integer id);
    void deleteById(Integer id);
    Boolean existsById(Integer id);
}
