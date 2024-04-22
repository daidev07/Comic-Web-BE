package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.AddChapterRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.dto.UpdateChapterRequest;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.repository.ChapterRepository;
import com.example.comicwebbe.repository.StoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private StoryRepository storyRepository;

    public List<Chapter> findListByStoryId(Long storyId) {
        return chapterRepository.findListChaptersByStoryId(storyId);
    }

    public Optional<Chapter> findById(Long chapterId) {
        return chapterRepository.findById(chapterId);
    }

    ////////////////////////////   THÊM CHAPTER
    @Transactional
    public void addChapter(Long storyId, AddChapterRequest addChapterRequest) {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            String timeNameImg = saveImage(addChapterRequest.getNoidung());

            Optional<Story> storyOptional = storyRepository.findById(storyId);
            if (storyOptional.isPresent()) {
                Chapter chapter = new Chapter(addChapterRequest.getSo(), addChapterRequest.getTen(), timeNameImg,
                        storyOptional.get(), currentTime, 0);

                chapterRepository.save(chapter);
            } else {
                throw new RuntimeException("Not found story with id:: " + addChapterRequest.getId_truyen());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error add new story:: " + e.getMessage());
        }
    }

    ////////////////////////////   XÓA CHAPTER
    @Transactional
    public ResponseEntity<String> deleteChapter(Long chapterId) {
        try {
            chapterRepository.deleteChapterByChapterId(chapterId);
            return ResponseEntity.ok("Deleted Success");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Undefined Error");
        }
    }

    /////////////////// CẬP NHẬT CHAPTER
    @Transactional
    public void updateChapter(Long chapterId, UpdateChapterRequest updateChapterRequest) {
        try {
            Optional<Chapter> existingChapterOptional = chapterRepository.findById(chapterId);

            if (existingChapterOptional.isPresent()) {
                Chapter existingChapter = existingChapterOptional.get();

                LocalDateTime currentTime = LocalDateTime.now();
                String timeNameImg = saveImage(updateChapterRequest.getNoidung());

                existingChapter.setSo(updateChapterRequest.getSo());
                existingChapter.setTen(updateChapterRequest.getTen());
                existingChapter.setNoidung(timeNameImg);
                existingChapter.setThoi_gian_dang(currentTime);

                chapterRepository.save(existingChapter);
            } else {
                throw new RuntimeException("Not found chapter with ID: " + chapterId);
            }
        } catch (Exception e) {
            System.out.println("error::" + e);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void increaseChapterView(Long chapterId) {
        try {
            Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
            if (chapterOptional.isPresent()) {
                Chapter chapter = chapterOptional.get();
                chapter.setView(chapter.getView() + 1);
                chapterRepository.save(chapter);
            } else {
                throw new RuntimeException("Not found chapter with ID: " + chapterId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error Increase View for Chapter: " + e.getMessage());
        }
    }

    public String saveImage(MultipartFile image) {
        try {
            String uploadDir = "uploads/";

            String uniqueFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu ảnh vào thư mục
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Trả về URL của ảnh đã lưu
            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Error save file:: " + e.getMessage());
        }
    }
}
