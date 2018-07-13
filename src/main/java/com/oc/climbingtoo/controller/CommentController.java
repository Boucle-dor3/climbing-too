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

        if (commentDTO.getMessage() == null || commentDTO.getMessage().trim().isEmpty()) {
            return "redirect:/sitepage/"+ idSite;
        }

        Comment comment = new Comment();
        comment.setMessage(commentDTO.getMessage().trim());
        comment.setSite(siteRepository.findById(idSite));
        commentRepository.save(comment);

        return "redirect:/sitepage/"+ idSite;
    }


    @PostMapping("/sitepage/{idSite}/comments/{idComment}/remove")
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

    @PostMapping("/sitepage/{idSite}/comments/{idParent}/answer")
    public String answerComment (@PathVariable Integer idSite,
                                 @ModelAttribute CommentDTO commentDTO,
                                 @PathVariable Integer idParent) {
        if (!siteRepository.existsById(idSite)) {
            throw new ResourceNotFoundException("idSite " + idSite + " not found");
        }

        if (commentDTO.getMessage() == null || commentDTO.getMessage().trim().isEmpty()) {
            return "redirect:/sitepage/"+ idSite;
        }
        if (!commentRepository.existsById(idParent)) {
            throw new ResourceNotFoundException("idComment " + idParent + " not found");
        }

        Comment comment = new Comment();
        comment.setMessage(commentDTO.getMessage().trim());
        comment.setSite(siteRepository.findById(idSite));
        comment.setParent(commentRepository.findById(idParent));
        commentRepository.save(comment);

        return "redirect:/sitepage/" + idSite;
    }
}
