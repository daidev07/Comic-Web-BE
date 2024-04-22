package com.example.comicwebbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table (name = "kiemtralike")
public class LikeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_binhluan")
    @JsonIgnoreProperties("comment")
    private Comment comment;

    private Boolean thich;

    public LikeComment(User user, Comment comment, Boolean thich) {
        this.user = user;
        this.comment = comment;
        this.thich = thich;
    }

    public LikeComment() {

    }
}
