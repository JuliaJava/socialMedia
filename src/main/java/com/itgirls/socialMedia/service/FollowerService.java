package com.itgirls.socialMedia.service;

import com.itgirls.socialMedia.dto.FollowerDto;
import com.itgirls.socialMedia.entity.Follower;
import com.itgirls.socialMedia.entity.User;
import com.itgirls.socialMedia.repository.FollowerRepository;
import com.itgirls.socialMedia.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private UserRepository userRepository;

    public Follower addNewFollower(Follower follower) {
        return followerRepository.save(follower);
    }

    public List<FollowerDto> getAllFollowers() {
        log.info("Get all followers");
        List<Follower> followers = followerRepository.findAll();

        List<FollowerDto> followerDtoList = new ArrayList<>();
        for (Follower follower: followers) {
            FollowerDto followerDto = new FollowerDto();
            followerDto.setId(follower.getId());
            followerDto.setFollowerId(follower.getFollowerId().getId());
            followerDto.setFolloweeId(follower.getFolloweeId().getId());
            followerDto.setDate(follower.getDate());
            followerDtoList.add(followerDto);
        }
        return followerDtoList;
    }

    public List<Follower> getFollowerById(Long id) {
        System.out.println("Get follower with id " + id);
        User user = userRepository.getUserById(id);
        return followerRepository.getFollowerByFollowerId(user);
    }

    public List<Object> getFollowersCount() {
        log.info("Get count of followers");
        List<Object> followers = followerRepository.getFollowersCount();
        return followers;
    }
}
