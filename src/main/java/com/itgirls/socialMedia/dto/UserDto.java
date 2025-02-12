package com.itgirls.socialMedia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itgirls.socialMedia.entity.Follower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    public Long id;
    public String name;
    public String email;
    public List<Follower> followerList;
    public long countFollowers;
    private String role;

}
