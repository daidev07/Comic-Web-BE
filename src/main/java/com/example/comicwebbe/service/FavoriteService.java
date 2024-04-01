package com.example.comicwebbe.service;

import com.example.comicwebbe.entity.Favorite;
import com.example.comicwebbe.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    public List<Favorite> getAllYeuThich() {
        return favoriteRepository.findAll();
    }
    public void deleteById(Long id){
        favoriteRepository.deleteById(id);
    }
    public void findById(Long id) {
        favoriteRepository.findById(id);
    }
}
