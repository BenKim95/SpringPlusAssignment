package com.example.springplusassignment.controller;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.CommentRequestDto;
import com.example.springplusassignment.sercurity.UserDetailsImpl;
import com.example.springplusassignment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment/{id}")
    public ResponseEntity<ApiResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(id, commentRequestDto, userDetails.getUser());
    }

    // 댓글 수정
    @PutMapping("/comment/{id}")
    public ResponseEntity<ApiResponseDto> editComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.editComment(id, commentRequestDto, userDetails.getUser());
    }

    //댓글 삭제
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id, userDetails.getUser());
    }
}
