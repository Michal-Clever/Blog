package pl.oskarpolak.blox.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.UserEntity;
import pl.oskarpolak.blox.models.forms.RegisterForm;
import pl.oskarpolak.blox.models.services.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user", produces ="application/json")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //przy wykorzystaniu register form nie trzeba tworzyć nowej metody
    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity registerNewUser(@RequestBody RegisterForm registerForm){
        if(userService.isUserExistByLogin(registerForm.getLogin())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("login busy");
        }
        userService.registerUser(registerForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/user")
    public ResponseEntity editUser(@RequestBody RegisterForm registerForm){
        userService.registerUser(registerForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
