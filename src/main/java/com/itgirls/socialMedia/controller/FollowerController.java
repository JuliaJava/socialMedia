package com.itgirls.socialMedia.controller;

import com.itgirls.socialMedia.dto.FollowerDto;
import com.itgirls.socialMedia.entity.Follower;
import com.itgirls.socialMedia.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FollowerController {

    public FollowerService followerService;

    @Autowired
    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @GetMapping("/followers")
    public List<FollowerDto> getAllFollowers() {
        return followerService.getAllFollowers();
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<?> getFollowerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(followerService.getFollowerById(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FollowerDto doesn't exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Exception while get FollowerDto by id");
        }
    }

    @PostMapping("/followers")
    public Follower addNewFollower(@RequestBody Follower Follower) {
        return followerService.addNewFollower(Follower);
    }

    @GetMapping("/followers/count")
    public ResponseEntity<?> getFollowersCount() {
        try {
            return ResponseEntity.ok(followerService.getFollowersCount());
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FollowerDto doesn't exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Exception while get FollowerDto by id");
        }
    }
}
