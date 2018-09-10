package pl.oskarpolak.blox.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.blox.models.CommentEntity;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.forms.CommentForm;
import pl.oskarpolak.blox.models.repositories.CommentRepository;
import pl.oskarpolak.blox.models.repositories.PostRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    UserService userService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    //d;aczego nie bierzemy posta z post repository?


    public void addComment(CommentForm commentForm, int postId) {

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setUser(userService.getUserData());
        commentEntity.setContent(commentForm.getContent());

        PostEntity postEntity = new PostEntity();
        postEntity.setId(postId);

        commentEntity.setPost(postEntity);
        commentRepository.save(commentEntity);
    }

    public void deleteComment(int commentId) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentId);

        commentRepository.delete(commentEntity);
    }


    public int getPostByCommentId (int id){

        return commentRepository.findById(id).get().getPost().getId();
    }

    public int getUserByCommentId(int id){

        return  commentRepository.findById(id).get().getUser().getId();
    }

}
