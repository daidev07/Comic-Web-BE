package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.UpdateChapterRequest;
import com.example.comicwebbe.dto.UpdateUserHistoryRequest;
import com.example.comicwebbe.dto.UserHistoryRequest;
import com.example.comicwebbe.entity.*;
import com.example.comicwebbe.repository.ChapterRepository;
import com.example.comicwebbe.repository.HistoryRepository;
import com.example.comicwebbe.repository.StoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    public void deleteById(Long id){
        historyRepository.deleteById(id);
    }
    public void findById(Long id) {
        historyRepository.findById(id);
    }


    public List<Story> getListReadStoryByUserId(Long userId) {
        return historyRepository.getListReadStoryByUserId(userId);
    }


    public List<History> findListHistoriesByUserId(Long userId) {
        return historyRepository.findListHistoriesByUserId(userId);
    }

    public List<Chapter> getListReadChapterByUserIdAndStoryId(Long userId, Long storyId) {
        return historyRepository.getListReadChapterByUserIdAndStoryId(userId, storyId);
    }

    public void addOrUpdateHistory(Long userId, Long storyId, Long chapterId) {
        LocalDateTime currentTime = LocalDateTime.now();

        User user = new User(userId);
        Story story = new Story(storyId);
        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);

        if (chapter == null) {
            chapter = new Chapter(chapterId);
            chapterRepository.save(chapter);
        }

        // Kiểm tra xem có bản ghi lịch sử nào tồn tại cho userId, storyId, chapterId đã cho hay không
        List<History> existingHistories = historyRepository.findByUserIdAndStoryIdAndChapterId(userId, storyId, chapterId);

        if (!existingHistories.isEmpty()) {
            for (History history : existingHistories) {
                history.setLan_cuoi_doc(currentTime);
                historyRepository.save(history);
            }
        } else {
            History history = new History(user, story, chapter, currentTime);
            historyRepository.save(history);
        }
    }


}
