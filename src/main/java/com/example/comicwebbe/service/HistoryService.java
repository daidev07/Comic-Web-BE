package com.example.comicwebbe.service;

import com.example.comicwebbe.entity.History;
import com.example.comicwebbe.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    public List<History> getAllLichSu() {
        return historyRepository.findAll();
    }
    public void deleteById(Long id){
        historyRepository.deleteById(id);
    }
    public void findById(Long id) {
        historyRepository.findById(id);
    }
}
