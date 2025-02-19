package com.itgirls.socialMedia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itgirls.socialMedia.entity.Follower;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min=1, max=10)
    public String name;
    @Email(message = "Email должен содержать @, .com")
    @NotBlank(message = "Email не должен быть пустым")
    public String email;
    @Min(18)
    @Max(99)
    private int age;
    public List<Follower> followerList;
    public long countFollowers;
    private String role;

}
