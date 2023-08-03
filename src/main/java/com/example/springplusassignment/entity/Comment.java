package com.example.springplusassignment.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table (name = "comments")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column (nullable = false)
    String contents;

    @ManyToOne // 외래키의 주인인 Comment에서 양방향으로 Post Entity 연결 -> Post조회시 Comment 나오기 위함
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne // 외래키의 주인인 Comment에서 단방향으로 User Entity 연결
    @JoinColumn(name = "user_id")
    private User user;

    public Comment (String contents, Post post, User user) {
        this.contents = contents;
        this.post = post;
        this.user = user;
    }
}
