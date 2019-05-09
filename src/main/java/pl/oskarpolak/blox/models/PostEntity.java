package pl.oskarpolak.blox.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
@Data
public class PostEntity {

        @Id
        @GeneratedValue
        private int id;
        private String title;
        private String context;
        @JoinColumn(name = "user_id")
        @ManyToOne
        private UserEntity user;

        @Column(name="creation_time")
        private LocalDateTime creationTime;

        //post tu to nazwa pola z post
        @OneToMany(mappedBy = "post")
        private List<CommentEntity> comments;

        //usuwanie postow
        //dodanie awatara
        //zamiana nicku
        //admin blokuje
        //do what you want!!!!!!!

}
