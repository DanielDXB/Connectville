package Main.service;

import Main.model.Group;
import Main.model.UserFollow;
import Main.repository.GroupPostRepository;
import Main.repository.GroupRepository;
import Main.repository.PostRepository;
import Main.repository.UserFollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupPostRepository groupPostRepository;
    private final PostRepository postRepository;
    private final UserFollowRepository userFollowRepository;

    public List<Group> getFollowedGroups(Integer userId) {

        List<UserFollow> userFollows = userFollowRepository.findByUserId(userId);
        List<Group> groups = new ArrayList<>();
        for (UserFollow u : userFollows) {

            groups.add(groupRepository.findById(u.getGroupId()).get());
        }

        return groups;
    }

    public List<Group> getNotFollowedGroups(Integer userId) {

        List<Group> groups = groupRepository.findAll();
        List<Group> followedGroups = getFollowedGroups(userId);
        return groups.stream()
                .filter(element -> !followedGroups.contains(element))
                .collect(Collectors.toList());
    }

    public List<Group> followGroup(Integer groupId, Integer userId) {

        UserFollow userFollow = userFollowRepository.findByUserIdAndGroupId(userId, groupId);
        if(userFollow == null) {
            userFollow = new UserFollow();
            userFollow.setGroupId(groupId);
            userFollow.setUserId(userId);
            userFollowRepository.save(userFollow);
        }
        return getFollowedGroups(userId);

    }

    @Transactional
    public List<Group> unfollowGroup(Integer groupId, Integer userId) {
        userFollowRepository.deleteByUserIdAndGroupId(userId, groupId);
        return getNotFollowedGroups(userId);
    }


}
