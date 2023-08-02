package com.example.springplusassignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String nickname;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

//    @Column(nullable = false)
//    @Enumerated(value = EnumType.STRING) // Enum타입을 String으로 DB에 저장
//    private UserRoleEnum roleEnum;

    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
}
