package Main.service;

import Main.model.UserLike;
import Main.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public UserLike createLike(UserLike userLike) {
        return likeRepository.save(userLike);
    }

    public void removeLike(Integer likeId) {
        likeRepository.deleteById(likeId);
    }

    public List<UserLike> getAllLikesByPostId(Integer postId) {
        return likeRepository.getAllByPostId(postId);
    }

    public List<UserLike> getAllLikesByUserId(Integer userId) {
        return likeRepository.getAllByUserId(userId);
    }

    public Boolean isLiked(Integer postId, Integer userId) {
        UserLike like = likeRepository.findByPostIdAndUserId(postId, userId);
        return like!=null;

    }

    public void likePost(Integer postId, Integer userId) {
        UserLike like = likeRepository.findByPostIdAndUserId(postId, userId);
        if(like==null) {
            UserLike newLike = new UserLike();
            newLike.setPostId(postId);
            newLike.setUserId(userId);
            likeRepository.save(newLike);
        }
        else {
            likeRepository.deleteById(like.getLikeId());
        }

    }
}
