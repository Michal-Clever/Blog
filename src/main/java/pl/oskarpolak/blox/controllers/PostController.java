package pl.oskarpolak.blox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.blox.models.CommentEntity;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.forms.CommentForm;
import pl.oskarpolak.blox.models.forms.PostForm;
import pl.oskarpolak.blox.models.services.CommentService;
import pl.oskarpolak.blox.models.services.PostService;
import pl.oskarpolak.blox.models.services.UserService;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @GetMapping("/addpost")
    public String post(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "addpost";
    }

    @PostMapping("/addpost")
    public String post(@ModelAttribute PostForm postForm) {

        if(!userService.isLogin()){
            return "redirect:/login";
        }
//przekierowanie niezalogowanych
//        if(userService.getUserData()==null)
//            return "redirect:/login";

        postService.addPost(postForm);

        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") int id, Model model){

        model.addAttribute("user", userService);
        Optional<PostEntity> postEntity = postService.getPostById(id);
        if(postEntity.isPresent()){
            model.addAttribute("post", postEntity.get());
        }

        model.addAttribute("commentForm", new CommentForm());
        return postEntity.isPresent() ? "post" : "redirect:/";
//        return   postService.getPostById(id).map(s -> {
//                      model.addAttribute("post", s);
//                      return "post";
//        }).orElse("redirect:/");
    }

    //path variable przyjąc id

    @PostMapping("/comment/{id}")
    public String comment(@ModelAttribute CommentForm commentForm, @PathVariable("id") int postId){
        if(!userService.isLogin()){
            return "redirect:/login";
        }
        commentService.addComment(commentForm, postId);
        return "redirect:/post/"+postId;
    }

    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") int commentId){

        int postNumber = commentService.getPostByCommentId(commentId);

        if(commentService.getUserByCommentId(commentId) == userService.getUserData().getId()) {
            commentService.deleteComment(commentId);
        }

        return "redirect:/post/"+postNumber;

    }

    //jak ukryć WYLOGUJ w templatce
    // th:if="${user.getId()}==null"

    @GetMapping("/deletePost/{id}")
    public String deletePost (@PathVariable("id") int postId){


        postService.deletePost(postId);


        return "redirect:/";

    }

}
