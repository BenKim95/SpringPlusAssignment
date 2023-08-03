package com.example.springplusassignment.service;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.CommentRequestDto;
import com.example.springplusassignment.entity.Comment;
import com.example.springplusassignment.entity.Post;
import com.example.springplusassignment.entity.User;
import com.example.springplusassignment.repository.CommentRepository;
import com.example.springplusassignment.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 댓글 작성
    @Transactional
    public ResponseEntity<ApiResponseDto> createComment(Long id, CommentRequestDto commentRequestDto, User user) {
        String contents = commentRequestDto.getContents();

        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "존재하지 않는 게시글에 댓글을 달 수 없습니다."));
        }

        commentRepository.save(new Comment(contents, post.get(), user));
        return ResponseEntity.status(201).body(new ApiResponseDto(HttpStatus.CREATED, "댓글 작성 완료"));
    }

    //댓글 수정
    @Transactional
    public ResponseEntity<ApiResponseDto> editComment(Long id, CommentRequestDto commentRequestDto, User user) {
        String contents = commentRequestDto.getContents();

        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "해당 댓글은 존재하지 않습니다."));
        } else if (!comment.get().getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "댓글 작성자만 수정할 수 있습니다."));
        }

        comment.get().setContents(contents);
        commentRepository.save(comment.get());
        return ResponseEntity.status(202).body(new ApiResponseDto(HttpStatus.ACCEPTED, "댓글 수정 완료"));
    }

    //댓글 삭제
    @Transactional
    public ResponseEntity<ApiResponseDto> deleteComment(Long id, User user) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "해당 댓글은 존재하지 않습니다."));
        } else if (!comment.get().getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "댓글 작성자만 삭제할 수 있습니다."));
        }

        commentRepository.delete(comment.get());
        return ResponseEntity.status(202).body(new ApiResponseDto(HttpStatus.ACCEPTED, "댓글 삭제 완료"));
    }
}
