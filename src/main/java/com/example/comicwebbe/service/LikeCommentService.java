package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.AddLikeCommentRequest;
import com.example.comicwebbe.entity.LikeComment;
import com.example.comicwebbe.entity.Comment;
import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.repository.CommentRepository;
import com.example.comicwebbe.repository.LikeCommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentService {
    @Autowired
    private LikeCommentRepository likeCommentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void addLike(AddLikeCommentRequest addLikeCommentRequest){
        User user = new User(addLikeCommentRequest.getId_user());
        Comment comment = new Comment(addLikeCommentRequest.getId_binhluan());
        LikeComment likeComment = new LikeComment(user, comment, true);

        likeCommentRepository.save(likeComment);

        Optional<Comment> commentExists = commentRepository.findById(addLikeCommentRequest.getId_binhluan());
        if(commentExists.isPresent()){
            comment = commentExists.get();
            comment.setLuot_thich(comment.getLuot_thich() + 1);
            commentRepository.save(comment);
        }
    }
    public Optional<LikeComment> getLikeByUserIdAndCommentId(Long userId, Long commentId){
        return likeCommentRepository.getLikeByUserIdAndCommentId(userId, commentId);
    }

    @Transactional
    public void deleteLikeByUserIdAndCommentId(Long userId, Long commentId){
        likeCommentRepository.deleteLikeByUserIdAndCommentId(userId, commentId);

        Optional<Comment> commentExists = commentRepository.findById(commentId);
        if(commentExists.isPresent()){
            Comment comment = commentExists.get();
            if(comment.getLuot_thich() > 0){
                comment.setLuot_thich(comment.getLuot_thich() - 1);
                commentRepository.save(comment);
            }
        }
    }


}
