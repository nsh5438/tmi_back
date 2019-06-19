package kr.hs.dgsw.backend.Controller;

import kr.hs.dgsw.backend.Domain.User;
import kr.hs.dgsw.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User Login(@RequestBody User user){
        return this.userService.Login(user);
    }

    @PostMapping("/register")
    public int Insert(@RequestBody User user) { return this.userService.Insert(user); }
}
