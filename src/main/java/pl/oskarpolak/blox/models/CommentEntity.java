package pl.oskarpolak.blox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class CommentEntity {

    @Id
    @GeneratedValue
    private int id;
    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "post_id")
    @ManyToOne
    @JsonIgnore
    private PostEntity post;

    @Column(name = "comment_time")
    private LocalDateTime commentTime;


}
