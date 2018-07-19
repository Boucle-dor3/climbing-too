package com.oc.climbingtoo.repository;

import com.oc.climbingtoo.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends org.springframework.data.repository.Repository<Comment, Integer> {
    Comment save (Comment entity);

    Comment findById (Integer idComment);
    void deleteById(Integer id);
    Boolean existsById(Integer id);

    @Query("SELECT c FROM Comment c WHERE c.site.id = :idSite AND c.parent IS NULL ")
    List<Comment> findParentsBySiteId(Integer idSite);

}
