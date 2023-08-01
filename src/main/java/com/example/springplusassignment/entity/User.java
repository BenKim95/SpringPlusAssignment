package com.example.springplusassignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
//    @Pattern(regexp = "^[a-zA-Z0-9]+")
    private String nickname;

    @Column (nullable = false)
//    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$")
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
}
