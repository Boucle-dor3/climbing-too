package com.oc.climbingtoo.controller;

import com.oc.climbingtoo.controller.dto.CommentDTO;
import com.oc.climbingtoo.entity.Comment;
import com.oc.climbingtoo.exception.ResourceNotFoundException;
import com.oc.climbingtoo.repositories.CommentDAO;
import com.oc.climbingtoo.repositories.SiteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class CommentController {


    private final CommentDAO commentDAO;


    private final SiteDAO siteDAO;


    @PostMapping("/sitepage/{idSite}/comments")
    public String createComment(@PathVariable Integer idSite,
                                @ModelAttribute CommentDTO commentDTO) {
        if (!siteDAO.existsById(idSite)) {
            throw new ResourceNotFoundException("idSite " + idSite + " not found");
        }

        if (commentDTO.getMessage() == null || commentDTO.getMessage().trim().isEmpty()) {
            return "redirect:/sitepage/"+ idSite;
        }

        Comment comment = new Comment();
        comment.setMessage(commentDTO.getMessage().trim());
        comment.setSite(siteDAO.findById(idSite));
        commentDAO.save(comment);

        return "redirect:/sitepage/"+ idSite;
    }


    @PostMapping("/sitepage/{idSite}/comments/{idComment}/remove")
    public String deleteComment(@PathVariable Integer idSite,
                                @PathVariable Integer idComment) {
        if (!siteDAO.existsById(idSite)) {
            throw new ResourceNotFoundException("idSite " + idSite + " not found");
        }
        if (!commentDAO.existsById(idComment)) {
            throw new ResourceNotFoundException("idComment " + idComment + " not found");
        }
        commentDAO.deleteById(idComment);

        return "redirect:/sitepage/" + idSite;
    }

    @PostMapping("/sitepage/{idSite}/comments/{idParent}/answer")
    public String answerComment (@PathVariable Integer idSite,
                                 @ModelAttribute CommentDTO commentDTO,
                                 @PathVariable Integer idParent) {
        if (!siteDAO.existsById(idSite)) {
            throw new ResourceNotFoundException("idSite " + idSite + " not found");
        }

        if (commentDTO.getMessage() == null || commentDTO.getMessage().trim().isEmpty()) {
            return "redirect:/sitepage/"+ idSite;
        }
        if (!commentDAO.existsById(idParent)) {
            throw new ResourceNotFoundException("idComment " + idParent + " not found");
        }

        Comment comment = new Comment();
        comment.setMessage(commentDTO.getMessage().trim());
        comment.setSite(siteDAO.findById(idSite));
        comment.setParent(commentDAO.findById(idParent));
        commentDAO.save(comment);

        return "redirect:/sitepage/" + idSite;
    }
}
