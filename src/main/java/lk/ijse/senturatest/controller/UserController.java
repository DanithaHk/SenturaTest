package lk.ijse.senturatest.controller;

import lk.ijse.senturatest.dto.User;
import lk.ijse.senturatest.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @GetMapping
    public String listUsers() throws Exception {
        return userService.listUsers();
    }

    @GetMapping("/{uid}")
    public String getUser(@PathVariable String uid) throws Exception {
        return userService.getUser(uid);
    }

    @PatchMapping("/{uid}")
    public String updateUser(@PathVariable String uid, @RequestBody User user) throws Exception {
        return userService.updateUser(uid, user);
    }

    @DeleteMapping("/{uid}")
    public String deleteUser(@PathVariable String uid) throws Exception {
        return userService.deleteUser(uid);
    }
}
