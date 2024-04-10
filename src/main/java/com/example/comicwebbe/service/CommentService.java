package com.example.comicwebbe.service;
import com.example.comicwebbe.dto.AddCommentRequest;
import com.example.comicwebbe.entity.Comment;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    public List<Comment> getListBinhLuanByStoryId(Long storyId) {
        return commentRepository.findListByStoryId(storyId);
    }
    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }
    public void findById(Long id){
        commentRepository.findById(id);
    }

    public void addComment(AddCommentRequest addCommentRequest){
        LocalDateTime currentDate = LocalDateTime.now();
        Comment comment = new Comment(new User(addCommentRequest.getUserId()), new Story(addCommentRequest.getStoryId()),
                addCommentRequest.getNoidung(), currentDate);
        commentRepository.save(comment);
    }
}
