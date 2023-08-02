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
    private String username;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

    // 관리자 권한 쓸 경우 해당 컬럼 활성화 및 Token과 UserDetailsImpl 부분 코드 수정 필요!!
//    @Column(nullable = false)
//    @Enumerated(value = EnumType.STRING) // Enum타입을 String으로 DB에 저장
//    private UserRoleEnum roleEnum;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
