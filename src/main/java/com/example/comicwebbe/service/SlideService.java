package com.example.comicwebbe.service;
import com.example.comicwebbe.dto.AddSlideRequest;
import com.example.comicwebbe.entity.Slide;
import com.example.comicwebbe.entity.Story;
import com.example.comicwebbe.repository.SlideRepository;
import com.example.comicwebbe.repository.StoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SlideService {
    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private StoryRepository storyRepository;

    public List<Slide> getAllSlides() {
        List<Slide> slides = slideRepository.findAll();
        return slides;
    }

    @Transactional
    public void addSlide(Long storyId, AddSlideRequest addSlideRequest){
        try {
            Optional<Story> storyOptional = storyRepository.findById(storyId);
            if (storyOptional.isPresent()) {
                Slide slide = new Slide(storyOptional.get().getId());
                slideRepository.save(slide);
            } else {
                throw new RuntimeException("Not found slide with storyId:: " + addSlideRequest.getId_truyen());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error add new story:: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteSlide(Long slideId){
        slideRepository.deleteById(slideId);
    }
}
