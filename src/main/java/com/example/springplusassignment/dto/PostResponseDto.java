package com.example.springplusassignment.dto;

import com.example.springplusassignment.entity.Comment;
import com.example.springplusassignment.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;

    public PostResponseDto (Post post, List<Comment> commentList)  {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = commentList.stream().map(CommentResponseDto::new).collect(Collectors.toList());
//        this.commentList = post.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        // Post Entity에서 mappedBy로 가지고 있는 Comment 객체의 CommentList들을 CommentResponseDto걸러서 출력
    }

    public PostResponseDto(Post post) { // 생성자 오버로딩으로 해결 단, 이게 맞는지는 의문
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
