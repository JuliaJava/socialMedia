package com.itgirls.socialMedia.controller;

import com.itgirls.socialMedia.dto.FollowerDto;
import com.itgirls.socialMedia.entity.Follower;
import com.itgirls.socialMedia.service.FollowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Подписчики", description = "Управление списком подписчиков")
public class FollowerController {

    public FollowerService followerService;

    @Autowired
    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @GetMapping("/followers")
    @Operation(summary = "Получение списка пользователей", description = "Позволяет получить список всех пользователей" +
            "и их подписчиков")
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
    public Follower addNewFollower(@RequestBody FollowerDto followerDto) {
        return followerService.addNewFollower(followerDto);
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
