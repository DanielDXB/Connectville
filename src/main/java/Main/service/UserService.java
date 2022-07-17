package Main.service;

import Main.model.Post;
import Main.model.User;
import Main.model.UserFollow;
import Main.repository.UserFollowRepository;
import Main.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserFollowRepository userFollowRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }


    public void followGroup(Integer groupId, Integer userId) {

        UserFollow userFollow = new UserFollow(userId, groupId);
        userFollowRepository.save(userFollow);
    }

    public void unfollowGroup(Integer groupId, Integer userId) {

        userFollowRepository.deleteByUserIdAndGroupId(userId, groupId);
    }

    public void addPictureToUser(Integer userId, MultipartFile picture) throws IOException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) return;
        fileUploadService.uploadFile(picture, "userImage/"+userId+".jpg");
    }
}
