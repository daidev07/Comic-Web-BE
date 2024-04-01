package com.example.comicwebbe.service;
import com.example.comicwebbe.entity.Comment;
import com.example.comicwebbe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    public Optional<Comment> getListBinhLuanByStoryId(Long storyId) {
        return commentRepository.findById(storyId);
    }
    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }
    public void findById(Long id){
        commentRepository.findById(id);
    }
}
