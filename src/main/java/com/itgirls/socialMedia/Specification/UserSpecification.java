package com.itgirls.socialMedia.Specification;

import com.itgirls.socialMedia.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasName(String name) {
        Specification<User> specification = ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), name); //where User.name = name
        });
        return specification;
    }

    public static Specification<User> hasEmail(String email) {
        Specification<User> specification = ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("email"), email); //where User.email = email
        });
        return specification;
    }


    //select * from User where email='Oleg@gmail.com'
    //select * from User where email='Oleg@gmail.com' and name='Oleg'
    //select * from User where email='Oleg@gmail.com' or name='Oleg'
}
