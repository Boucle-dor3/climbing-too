package com.oc.climbingtoo.controller;

import com.oc.climbingtoo.controller.dto.CommentDTO;
import com.oc.climbingtoo.entity.Comment;
import com.oc.climbingtoo.exception.ResourceNotFoundException;
import com.oc.climbingtoo.repository.CommentRepository;
import com.oc.climbingtoo.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SiteRepository siteRepository;


    @PostMapping("/sitepage/{idSite}/comments")
    public String createComment(@PathVariable Integer idSite,
                                @ModelAttribute CommentDTO commentDTO) {
        if (!siteRepository.existsById(idSite)) {
            throw new ResourceNotFoundException("idSite " + idSite + " not found");
        }

        Comment comment = new Comment();
        comment.setMessage(commentDTO.getMessage());
        comment.setSite(siteRepository.findById(idSite));
        commentRepository.save(comment);

        return "redirect:/sitepage/"+ idSite;
    }


    @DeleteMapping("/sitepage/{idSite}/comments/{idComment}")
    public String deleteComment(@PathVariable Integer idSite,
                                @PathVariable Integer idComment) {
        if (!siteRepository.existsById(idSite)) {
            throw new ResourceNotFoundException("idSite " + idSite + " not found");
        }
        if (!commentRepository.existsById(idComment)) {
            throw new ResourceNotFoundException("idComment " + idComment + " not found");
        }
        commentRepository.deleteById(idComment);

        return "redirect:/sitepage/" + idSite;
    }
}
