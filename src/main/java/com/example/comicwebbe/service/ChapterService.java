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
import java.util.concurrent.CompletableFuture;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private StoryRepository storyRepository;

    public List<Chapter> findListByStoryId(Long storyId) {
        return chapterRepository.findListByStoryId(storyId);
    }

    public Optional<Chapter> findById(Long chapterId) {
        return chapterRepository.findById(chapterId);
    }
    ////////////////////////////   THÊM CHAPTER
    @Transactional
    public void addChapter(Long storyId, AddChapterRequest addChapterRequest) {
        try {
            MultipartFile noidung = addChapterRequest.getNoidung();
            String noidunganh = noidung.getOriginalFilename();

            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String timeNameImg = currentTime + "_" + noidunganh;
            try {
                Path uploadPath = Paths.get("src", "main", "resources", "uploads");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                // Sao chép dữ liệu từ InputStream của MultipartFile vào tập tin trên đĩa
                Files.copy(noidung.getInputStream(), uploadPath.resolve(timeNameImg), StandardCopyOption.REPLACE_EXISTING);// Xác định đường dẫn của thư mục uploads
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi lưu trữ tệp ảnh: " + e.getMessage());
            }
            System.out.println("CHẠY TỚI ĐÂY CHƯA???");

            // Tìm câu chuyện dựa trên id_truyen từ AddChapterRequest
            Optional<Story> storyOptional = storyRepository.findById(storyId);
            if (storyOptional.isPresent()) {
                Chapter chapter = new Chapter();
                chapter.setSo(addChapterRequest.getSo());
                chapter.setTen(addChapterRequest.getTen());
                chapter.setNoidung(timeNameImg);
                // Gán story cho chương
                chapter.setStory(storyOptional.get());
                // Lưu chương mới vào cơ sở dữ liệu
                chapterRepository.save(chapter);
            } else {
                throw new RuntimeException("Không tìm thấy câu chuyện với id: " + addChapterRequest.getId_truyen());
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi thêm chương mới: " + e.getMessage());
        }
    }

    ////////////////////////////   XÓA CHAPTER

    @Transactional
    public ResponseEntity<String> deleteChapter(Long chapterId) {
        try {
            chapterRepository.deleteChapterByStoryIdAndChapterId(chapterId);
            return ResponseEntity.ok("Xóa thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi không xác định");
        }
    }

    /////////////////// CẬP NHẬT CHAPTER
    @Transactional
    public void updateChapter(Long chapterId, UpdateChapterRequest updateChapterRequest) {
        try {
            Optional<Chapter> existingChapterOptional = chapterRepository.findById(chapterId);

            if (existingChapterOptional.isPresent()) {
                Chapter existingChapter = existingChapterOptional.get();

                MultipartFile noidung = updateChapterRequest.getNoidung();
                String noidunganh = noidung.getOriginalFilename();

                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String timeNameImg = currentTime + "_" + noidunganh;

                try {
                    Path uploadPath = Paths.get("src", "main", "resources", "uploads");
                    // Tạo thư mục uploads nếu nó chưa tồn tại
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    // Sao chép dữ liệu từ InputStream của MultipartFile vào tập tin trên đĩa
                    Files.copy(noidung.getInputStream(), uploadPath.resolve(timeNameImg), StandardCopyOption.REPLACE_EXISTING);// Xác định đường dẫn của thư mục uploads
                } catch (IOException e) {
                    throw new RuntimeException("Lỗi khi lưu trữ tệp ảnh: " + e.getMessage());
                }

                existingChapter.setSo(updateChapterRequest.getSo());
                existingChapter.setTen(updateChapterRequest.getTen());
                existingChapter.setNoidung(timeNameImg);

                chapterRepository.save(existingChapter);
            } else {
                throw new RuntimeException("Không tìm thấy chương có ID: " + chapterId);
            }
        } catch (Exception e) {
            System.out.println("error::" + e);
            throw new RuntimeException(e);
        }
    }
}
