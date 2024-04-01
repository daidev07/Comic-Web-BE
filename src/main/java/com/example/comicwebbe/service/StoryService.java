package com.example.comicwebbe.service;

import com.example.comicwebbe.dto.UpdateStoryRequest;
import com.example.comicwebbe.dto.AddStoryRequest;
import com.example.comicwebbe.entity.Category;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.entity.StoryCategory;
import com.example.comicwebbe.repository.CategoryRepository;
import com.example.comicwebbe.repository.StoryRepository;
import com.example.comicwebbe.repository.StoryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteById(Long id){
        storyRepository.deleteById(id);
    }
    public void findById(Long id) {
        storyRepository.findById(id);
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

    public void updateStory (UpdateStoryRequest updateStoryRequest){
        try {
            String base64Content = updateStoryRequest.getAvt();
            byte[] avt = Base64.getDecoder().decode(base64Content);

            Story story = new Story();
            story.setTen(updateStoryRequest.getTen());
            story.setAvt(avt);
            story.setGioithieu(updateStoryRequest.getGioithieu());
            story.setTacgia(updateStoryRequest.getTacgia());
            story.setView(updateStoryRequest.getView());

            storyRepository.save(story);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error");
        }
    }

}
