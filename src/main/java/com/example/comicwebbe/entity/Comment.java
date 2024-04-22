package com.example.comicwebbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table (name = "binhluan")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_truyen")
    private Story story;

    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("comment")
    private List<LikeComment> likeComments;

    private String noidung;
    private LocalDateTime thoi_gian_dang;
    private Integer luot_thich;

    public Comment(User user, Story story, String noidung, LocalDateTime thoi_gian_dang, Integer luot_thich) {
        this.user = user;
        this.story = story;
        this.noidung = noidung;
        this.thoi_gian_dang = thoi_gian_dang;
        this.luot_thich = luot_thich;
    }

    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }
}
