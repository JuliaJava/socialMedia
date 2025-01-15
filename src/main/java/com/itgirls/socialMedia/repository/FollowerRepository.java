package com.itgirls.socialMedia.repository;

import com.itgirls.socialMedia.entity.Follower;
import com.itgirls.socialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    List<Follower> getFollowerByFollowerId(User user);

    List<Follower> getFollowerByFolloweeId(User user);

}
