package com.itgirls.socialMedia.repository;

import com.itgirls.socialMedia.entity.Follower;
import com.itgirls.socialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    List<Follower> getFollowerByFollowerId(User user);

    List<Follower> getFollowerByFolloweeId(User user);

    @Query("Select followeeId.id, followeeId.name, count(followerId) " +
            "from Follower " +
            "group by followeeId.id, followeeId.name")
    List<Object> getFollowersCount();
}
