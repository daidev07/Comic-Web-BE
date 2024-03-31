package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.SuaTruyen;
import com.example.comicwebbe.dto.ThemTruyen;
import com.example.comicwebbe.entity.LichSu;
import com.example.comicwebbe.entity.Truyen;
import com.example.comicwebbe.repository.LichSuRepository;
import com.example.comicwebbe.repository.TruyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruyenService {
    @Autowired
    private TruyenRepository truyenRepository;
    public List<Truyen> getAllTruyen() {
        return truyenRepository.findAll();
    }
    public void deleteById(Long id){
        truyenRepository.deleteById(id);
    }
    public void findById(Long id) {
        truyenRepository.findById(id);
    }

    public void addStory (ThemTruyen themTruyen){
        Truyen truyen = new Truyen();
        truyen.setTen(themTruyen.getTen());
        truyen.setAvt(themTruyen.getAvt());
        truyen.setGioithieu(themTruyen.getGioithieu());
        truyen.setTacgia(themTruyen.getTacgia());
        truyen.setView(themTruyen.getView());

        truyenRepository.save(truyen);
    }

    public void updateStory (SuaTruyen suaTruyen){
        try {
            Truyen truyen = new Truyen();
            truyen.setTen(suaTruyen.getTen());
            truyen.setAvt(suaTruyen.getAvt());
            truyen.setGioithieu(suaTruyen.getGioithieu());
            truyen.setTacgia(suaTruyen.getTacgia());
            truyen.setView(suaTruyen.getView());

            truyenRepository.save(truyen);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }
    }
}
