package Main.controller;

import Main.dto.PostDTO;
import Main.model.Group;
import Main.repository.GroupRepository;
import Main.service.GroupService;
import Main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final PostService postService;
    private final GroupService groupService;

    @GetMapping("/")
    public List<Group> getGroupsFromDB() {

        return groupRepository.findAll();
    }

    @GetMapping("/posts/{groupId}")
    public List<PostDTO> getPostsFromGroup(@PathVariable Integer groupId){

        return postService.getPostByGroup(groupId);
    }

    @GetMapping("/pinnedPosts/{groupId}")
    public List<PostDTO> getPinnedPostsFromGroup(@PathVariable Integer groupId){

        return postService.getPinnedPostsByGroup(groupId);
    }


    @GetMapping("/followed/{userId}")
    public List<Group> getFollowedGroups(@PathVariable Integer userId) {
        return groupService.getFollowedGroups(userId);
    }

    @GetMapping("/not-followed/{userId}")
    public List<Group> getNotFollowedGroups(@PathVariable Integer userId) {
        return groupService.getNotFollowedGroups(userId);
    }

    @PostMapping("/follow/{groupId}/{userId}")
    public List<Group> followGroup(@PathVariable Integer groupId, @PathVariable Integer userId) {
        return groupService.followGroup(groupId, userId);
    }

    @PostMapping("/unfollow/{groupId}/{userId}")
    public List<Group> unfollowGroup(@PathVariable Integer groupId, @PathVariable Integer userId) {
        return groupService.unfollowGroup(groupId, userId);
    }



}
