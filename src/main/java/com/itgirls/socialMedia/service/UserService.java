package com.itgirls.socialMedia.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itgirls.socialMedia.Specification.UserSpecification;
import com.itgirls.socialMedia.dto.UserDto;
import com.itgirls.socialMedia.entity.Follower;
import com.itgirls.socialMedia.entity.User;
import com.itgirls.socialMedia.repository.FollowerRepository;
import com.itgirls.socialMedia.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private EntityManager entityManager;

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        System.out.println("Get user with id " + id);
        return userRepository.getUserById(id);
    }

    public User getUserByName(String userName) {
        System.out.println("Get user by name " + userName);
        return null;
    }

    public List<UserDto> getAllUsersWithFollowers() {
        log.info("Get all users");
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        for(User user: users) {
            List<Follower> followerList = followerRepository.getFollowerByFolloweeId(user);
            UserDto userDto = new ObjectMapper().convertValue(user, UserDto.class);
            userDto.setFollowerList(followerList);
            usersDto.add(userDto);
        }
        return usersDto;
    }

    public long getUsersCount() {
        log.info("Get count of all users");
        return userRepository.getUsersCount();
    }

    public List<User> getUsersByNameOrEmail(String name, String email) {

        Specification<User> specification = Specification.where(null); //select * from User
        if (StringUtils.isNotEmpty(name)) {
            specification = specification.and(UserSpecification.hasName(name)); //select * from User + where email='Oleg@gmail.com'
        }
        if (StringUtils.isNotEmpty(email)) {
            specification = specification.and(UserSpecification.hasEmail(email));//select * from User + where email='Oleg@gmail.com' and name='Oleg'
        }

        return userRepository.findAll(specification);
    }
}
