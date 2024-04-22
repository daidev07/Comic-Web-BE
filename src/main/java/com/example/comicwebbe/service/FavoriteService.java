package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.UserFavoriteRequest;
import com.example.comicwebbe.entity.Favorite;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.User;
import com.example.comicwebbe.repository.FavoriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public Optional<Favorite> getOneByUserIdAndStoryId(Long userId, Long storyId) {
        return favoriteRepository.findOneByUserIdAndStoryId(userId, storyId);
    }

    @Transactional
    public void deleteFavoriteByUserIdAndStoryId(Long userId, Long storyId){
        favoriteRepository.deleteFavoriteByUserIdAndStoryId(userId, storyId);
    }
    public void deleteById(Long id) {
        favoriteRepository.deleteById(id);
    }

    public void findById(Long id) {
        favoriteRepository.findById(id);
    }

    public void addOne(UserFavoriteRequest userFavoriteRequest) {
        User user = new User(userFavoriteRequest.getId_user());
        Story story = new Story(userFavoriteRequest.getId_story());
        Favorite favorite = new Favorite(user, story);
        favoriteRepository.save(favorite);
    }

    public List<Story> getListFavoriteStoryByUserId(Long userId) {
        return favoriteRepository.findListFavoriteStoryByUserId(userId);
    }
}
