package Main.controller;

import Main.exception.UnauthorizedException;
import Main.model.User;
import Main.repository.UserFollowRepository;
import Main.repository.UserRepository;
import Main.model.Credentials;
import Main.service.UserService;
import Main.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsersFromDb() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PostMapping("/login")
    //TODO: needs to be changed to userdto at some  point
    public User userLogin(@RequestBody Credentials credentials, HttpServletResponse response) throws UnauthorizedException {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if(userOptional.isPresent()) {
            Cookie cookie = new Cookie("auth", Utils.generateToken(userOptional.get()));
            response.addCookie(cookie);
            return userOptional.get();

        } else {
//            throw new UnauthorizedException("Credentials are wrong");
            User user = new User();
            user.setUsername("wrong");
            return user;
        }
    }

    @PostMapping("follow/{userId}/{groupId}")
    public void followGroup(@PathVariable Integer userId, @PathVariable Integer groupId) {

        userService.followGroup(groupId, userId);

    }

    @Transactional
    @DeleteMapping("unfollow/{userId}/{groupId}")
    public void unfollowGroup(@PathVariable Integer userId, @PathVariable Integer groupId) {

        userService.unfollowGroup(groupId, userId);
    }
}
