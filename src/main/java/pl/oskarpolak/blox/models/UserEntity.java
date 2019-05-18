package pl.oskarpolak.blox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import javafx.geometry.Pos;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;
    @Column(name = "has_admin")
    private boolean hasAdmin;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<PostEntity> posts;


}
