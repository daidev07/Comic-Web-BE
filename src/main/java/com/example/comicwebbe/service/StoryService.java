package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.*;
import com.example.comicwebbe.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
import java.util.stream.Stream;

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private StoryCategoryRepository storyCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private LikeCommentRepository likeCommentRepository;

    public List<Story> getAllTruyen() {
        List<Story> stories = storyRepository.findAll();
        for (Story story : stories) {
            List<Chapter> chapters = chapterRepository.findListChaptersByStoryId(story.getId());
            story.setChapters(chapters);
        }
        return stories;
    }

    @Transactional
    public void deleteStory(Long storyId) {
        historyRepository.deleteHistoriesByStoryId(storyId);
        chapterRepository.deleteChaptersByStoryId(storyId);
        storyCategoryRepository.deleteStoryCategoryByStoryId(storyId);
        likeCommentRepository.deleteLikeCommentByStoryId(storyId);
        commentRepository.deleteCommentsByStoryId(storyId);
        favoriteRepository.deleteFavoritesByStoryId(storyId);
        slideRepository.deleteSlideByStoryId(storyId);
        storyRepository.deleteById(storyId);
    }

    public Optional<Story> findById(Long id) {
        return storyRepository.findById(id);
    }

    public List<Category> getCategoriesForStory(Long storyId) {
        return storyCategoryRepository.findCategoriesByStoryId(storyId);
    }

    //CRUD Story
    public void addStory(AddStoryRequest addStoryRequest) {

        LocalDateTime currentTime = LocalDateTime.now();

        String avtImageUrl = saveImage(addStoryRequest.getAvtFile());
        Story story = new Story(addStoryRequest.getTen(), avtImageUrl, addStoryRequest.getGioithieu(),
                addStoryRequest.getTacgia(), currentTime);

        Story newStory = storyRepository.save(story);

        for (Long idTheLoai : addStoryRequest.getIdTheLoais()) {
            // Kiểm tra xem thể loại có tồn tại không
            Optional<Category> cateOptional = categoryRepository.findById(idTheLoai);
            if (cateOptional.isPresent()) {
                StoryCategory storyCategory = new StoryCategory(newStory.getId(), idTheLoai);
                storyCategoryRepository.save(storyCategory);
            } else {
                throw new RuntimeException("Thể loại không tồn tại: " + idTheLoai);
            }
        }
    }

    @Transactional
    public void updateStory(Long storyId, UpdateStoryRequest updateStoryRequest) {
        try {
            // Kiểm tra story có tồn tại không
            Optional<Story> existingStoryOptional = storyRepository.findById(storyId);
            if (existingStoryOptional.isPresent()) {
                Story existingStory = existingStoryOptional.get();

                // Xóa các thể loại story cũ
                storyCategoryRepository.deleteStoryCategoryByStoryId(storyId);

                LocalDateTime currentTime = LocalDateTime.now();
                String avtImageUrl = saveImage(updateStoryRequest.getAvtFile());

                existingStory.setAvt(avtImageUrl);
                existingStory.setTen(updateStoryRequest.getTen());
                existingStory.setGioithieu(updateStoryRequest.getGioithieu());
                existingStory.setTacgia(updateStoryRequest.getTacgia());
                existingStory.setThoi_gian_dang(currentTime);

                Story updatedStory = storyRepository.save(existingStory);

                // Thêm các thể loại mới
                for (Long idTheLoai : updateStoryRequest.getIdTheLoais()) {
                    Optional<Category> cateOptional = categoryRepository.findById(idTheLoai);
                    if (cateOptional.isPresent()) {
                        StoryCategory storyCategory = new StoryCategory(updatedStory.getId(), idTheLoai);
                        storyCategoryRepository.save(storyCategory);
                    } else {
                        throw new RuntimeException("Thể loại không tồn tại: " + idTheLoai);
                    }
                }
            } else {
                throw new RuntimeException("Không tìm thấy truyện có ID: " + storyId);
            }
        } catch (Exception e) {
            System.out.println("error::" + e);
            throw new RuntimeException(e);
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
            throw new RuntimeException("Lỗi khi lưu trữ tệp ảnh: " + e.getMessage());
        }
    }

}
