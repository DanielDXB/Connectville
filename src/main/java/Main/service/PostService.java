package Main.service;

import Main.dto.PostDTO;
import Main.model.*;
import Main.repository.CommentRepository;
import Main.repository.GroupPostRepository;
import Main.repository.LikeRepository;
import Main.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final GroupPostRepository groupPostRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final FileUploadService fileUploadService;

    private final UserService userService;

    public Post createPost(Integer groupId, Post post) {
        post.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        GroupPost groupPost = new GroupPost(groupId, post.getPostId());
        Post addedPost = postRepository.save(post);
        groupPost.setPostId(post.getPostId());
        groupPostRepository.save(groupPost);
        return addedPost;
    }

    public void addPictureToPost(Integer postId, MultipartFile picture) throws IOException {
        Post post = postRepository.findById(postId).get();
        post.setImage("postImages/"+postId+".jpg");
        postRepository.save(post);
        fileUploadService.uploadFile(picture, "postImages/" + postId + ".jpg");
    }

    @Transactional
    public void deletePost(Integer postId) {

        groupPostRepository.deleteByPostId(postId);
        List<UserComment> userComments = commentRepository.getAllByPostId(postId);
        List<UserLike> userLikes = likeRepository.getAllByPostId(postId);
        for (UserComment userComment : userComments) {
            commentRepository.deleteById(userComment.getCommentId());
        }
        for (UserLike userLike : userLikes) {
            likeRepository.deleteById(userLike.getLikeId());
        }


        postRepository.deleteById(postId);

    }

    public void editPost(Post post) {
        Post changedPost = postRepository.getById(post.getPostId());
        //changedPost.setTag(post.getTag());
        changedPost.setPinned(post.isPinned());
        changedPost.setImage(post.getImage());
        changedPost.setLink(post.getLink());
        changedPost.setText(post.getText());
        changedPost.setTitle(post.getTitle());
        changedPost.setTopic(post.getTopic());
    }

    public PostDTO getPostById(Integer id) {
        Post post = postRepository.findById(id).get();
        User user = userService.getUserById(post.getCreateUser());
        return new PostDTO(user, post);
    }

    public PostDTO getPostByTitle(String title) {
        Post post = postRepository.getPostByTitle(title);
        User user = userService.getUserById(post.getCreateUser());
        return new PostDTO(user, post);
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            User user = userService.getUserById(post.getCreateUser());
            postDTOs.add(new PostDTO(user, post));
        }
//        postDTOs.sort(Comparator.comparing(PostDTO::getCreateTimestamp));
        return postDTOs;
    }

    public List<PostDTO> getPostByGroup(Integer groupId) {
        List<GroupPost> groupPosts = groupPostRepository.findGroupPostByGroupId(groupId);
        List<Post> posts = new ArrayList<>();
        for(GroupPost groupPost : groupPosts) {
            posts.add(postRepository.findById(groupPost.getPostId()).get());
        }
        posts.sort(Comparator.comparing(Post::getCreateTimestamp).reversed());
        //convert posts to postDTOs
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            User user = userService.getUserById(post.getCreateUser());
            postDTOs.add(new PostDTO(user, post));
        }
        return postDTOs;
    }

    public List<PostDTO> getPinnedPostsByGroup(Integer groupId) {
        List<GroupPost> groupPosts = groupPostRepository.findGroupPostByGroupId(groupId);
        List<Post> posts = new ArrayList<>();
        for(GroupPost groupPost : groupPosts) {
            Optional<Post> postFromDb = postRepository.findById(groupPost.getPostId());
            if(postFromDb.isPresent() && postFromDb.get().isPinned()) {
                posts.add(postFromDb.get());
            }
        }
        posts.sort(Comparator.comparing(Post::getCreateTimestamp).reversed());
        //convert posts to postDTOs
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            User user = userService.getUserById(post.getCreateUser());
            postDTOs.add(new PostDTO(user, post));
        }
        return postDTOs;
    }

    public List<PostDTO> getAllPinnedPosts(){
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            if(post.isPinned()) {
                if(groupPostRepository.findGroupPostByPostId(post.getPostId()).get().getGroupId()!=4) {
                    User user = userService.getUserById(post.getCreateUser());
                    postDTOs.add(new PostDTO(user, post));
                }
            }
        }
        postDTOs.sort(Comparator.comparing(PostDTO::getCreateTimestamp));
        return postDTOs;
    }



    public Post pinPost(Integer postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if(!postOptional.isPresent()) return null;
        Post post = postOptional.get();
        if(post.isPinned()) {
            post.setPinned(false);
        } else {
            post.setPinned(true);
        }
        return postRepository.save(post);
    }

}
