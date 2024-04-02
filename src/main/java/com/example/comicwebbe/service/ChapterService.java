package com.example.comicwebbe.service;
import com.example.comicwebbe.dto.AddChapterRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Chapter;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.repository.ChapterRepository;
import com.example.comicwebbe.repository.StoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private StoryRepository storyRepository;
    public List<Chapter> getAllChuong() {
        return chapterRepository.findAll();
    }
    public void deleteById(Long id){
        chapterRepository.deleteById(id);
    }
    public void findById(Long id){
        chapterRepository.findById(id);
    }
    @Transactional
    public void addChapter (Long storyId, AddChapterRequest addChapterRequest){
        try {
            // Tìm câu chuyện dựa trên id_truyen từ AddChapterRequest
            Optional<Story> storyOptional = storyRepository.findById(storyId);
            if (storyOptional.isPresent()) {
                String base64Content = addChapterRequest.getNoidung();
                byte[] noidung = Base64.getDecoder().decode(base64Content);

                Chapter chapter = new Chapter();
                chapter.setSo(addChapterRequest.getSo());
                chapter.setTen(addChapterRequest.getTen());
                chapter.setNoidung(noidung);
                chapter.setIs_reading(addChapterRequest.getIs_reading());

                // Gán câu chuyện cho chương
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
//    @Transactional
//    public void updateStory(Long storyId, UpdateStoryRequest updateStoryRequest) {
//        try {
//            // Kiểm tra xem câu chuyện có tồn tại không
//            Optional<Story> existingStoryOptional = storyRepository.findById(storyId);
//            if (existingStoryOptional.isPresent()) {
//                Story existingStory = existingStoryOptional.get();
//
//                // Xóa các thể loại câu chuyện cũ
//                storyCategoryRepository.deleteStoryCategoryByStoryId(storyId);
//
//                // Cập nhật thông tin câu chuyện
//                String base64Content = updateStoryRequest.getAvt();
//                byte[] avt = Base64.getDecoder().decode(base64Content);
//
//                existingStory.setTen(updateStoryRequest.getTen());
//                existingStory.setAvt(avt);
//                existingStory.setGioithieu(updateStoryRequest.getGioithieu());
//                existingStory.setTacgia(updateStoryRequest.getTacgia());
//                existingStory.setView(updateStoryRequest.getView());
//
//                Story updatedStory = storyRepository.save(existingStory);
//
//                // Thêm các thể loại mới
//                for (Long idTheLoai : updateStoryRequest.getIdTheLoais()) {
//                    Optional<Category> cateOptional = categoryRepository.findById(idTheLoai);
//                    if (cateOptional.isPresent()) {
//                        StoryCategory storyCategory = new StoryCategory(updatedStory.getId(), idTheLoai);
//                        storyCategoryRepository.save(storyCategory);
//                    } else {
//                        throw new RuntimeException("Thể loại không tồn tại: " + idTheLoai);
//                    }
//                }
//            } else {
//                throw new RuntimeException("Không tìm thấy câu chuyện có ID: " + storyId);
//            }
//        } catch (Exception e) {
//            System.out.println("error::" + e);
//            throw new RuntimeException(e);
//        }
//    }
}
