package com.example.comicwebbe.service;
import com.example.comicwebbe.dto.AddCommentRequest;
import com.example.comicwebbe.entity.Comment;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.repository.LikeCommentRepository;
import com.example.comicwebbe.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeCommentRepository likeCommentRepository;

    public List<Comment> getListBinhLuanByStoryId(Long storyId) {
        return commentRepository.findListCommentsByStoryId(storyId);
    }

    public Optional<Comment> findById(Long commentId){
        return commentRepository.findById(commentId);
    }

    @Transactional
    public void deleteCommentByCommentId(Long commentId){
        likeCommentRepository.deleteListByCommentId(commentId);
        commentRepository.deleteCommentByCommentId(commentId);
    }

    public void addComment(AddCommentRequest addCommentRequest){
        LocalDateTime currentDate = LocalDateTime.now();
        Comment comment = new Comment(new User(addCommentRequest.getUserId()), new Story(addCommentRequest.getStoryId()),
                addCommentRequest.getNoidung(), currentDate, 0);
        commentRepository.save(comment);
    }
}
