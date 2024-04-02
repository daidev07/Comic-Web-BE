package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.repository.CategoryRepository;
import com.example.comicwebbe.repository.StoryRepository;
import com.example.comicwebbe.repository.StoryCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private StoryCategoryRepository storyCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Story> getAllTruyen() {
        return storyRepository.findAll();
    }

    @Transactional
    public void deleteStoryAndRelatedCategories(Long storyId) {
        storyCategoryRepository.deleteStoryCategoryByStoryId(storyId);
        // Sau đó xóa truyện từ bảng Story
        storyRepository.deleteById(storyId);
    }
    public void deleteById(Long id){
        storyRepository.deleteById(id);
    }
    public Optional<Story> findById(Long id) {
        return storyRepository.findById(id);
    }
//    CRUD Story
    public void addStory (AddStoryRequest addStoryRequest){
        String base64Content = addStoryRequest.getAvt();
        byte[] avt = Base64.getDecoder().decode(base64Content);

        Story story = new Story();
        story.setTen(addStoryRequest.getTen());
        story.setAvt(avt);
        story.setGioithieu(addStoryRequest.getGioithieu());
        story.setTacgia(addStoryRequest.getTacgia());
        story.setView(addStoryRequest.getView());

        Story newStory = storyRepository.save(story);

        for (Long idTheLoai : addStoryRequest.getIdTheLoais()) {
            // Kiểm tra xem thể loại có tồn tại không
            Optional<Category> cateOptional = categoryRepository.findById(idTheLoai);
            if (cateOptional.isPresent()) {
                StoryCategory storyCategory = new StoryCategory(newStory.getId(), idTheLoai);
                storyCategoryRepository.save(storyCategory);
            } else {
                // Xử lý khi thể loại không tồn tại
                throw new RuntimeException("Thể loại không tồn tại: " + idTheLoai);
            }
        }
    }
    @Transactional
    public void updateStory(Long storyId, UpdateStoryRequest updateStoryRequest) {
        try {
            // Kiểm tra xem câu chuyện có tồn tại không
            Optional<Story> existingStoryOptional = storyRepository.findById(storyId);
            if (existingStoryOptional.isPresent()) {
                Story existingStory = existingStoryOptional.get();

                // Xóa các thể loại câu chuyện cũ
                storyCategoryRepository.deleteStoryCategoryByStoryId(storyId);

                // Cập nhật thông tin câu chuyện
                String base64Content = updateStoryRequest.getAvt();
                byte[] avt = Base64.getDecoder().decode(base64Content);

                existingStory.setTen(updateStoryRequest.getTen());
                existingStory.setAvt(avt);
                existingStory.setGioithieu(updateStoryRequest.getGioithieu());
                existingStory.setTacgia(updateStoryRequest.getTacgia());
                existingStory.setView(updateStoryRequest.getView());

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
                throw new RuntimeException("Không tìm thấy câu chuyện có ID: " + storyId);
            }
        } catch (Exception e) {
            System.out.println("error::" + e);
            throw new RuntimeException(e);
        }
    }
    public List<Category> getCategoriesForStory(Long storyId) {
        return storyCategoryRepository.findCategoriesByStoryId(storyId);
    }


}
