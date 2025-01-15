package com.itgirls.socialMedia.repository;

import com.itgirls.socialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);

    List<User> getAllByNameOrderByIdDesc(String name);

}
