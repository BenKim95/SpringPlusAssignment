package com.example.springplusassignment.controller;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.PostRequestDto;
import com.example.springplusassignment.dto.PostResponseDto;
import com.example.springplusassignment.sercurity.UserDetailsImpl;
import com.example.springplusassignment.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class PostController {

    private final PostService postService;

    // 글 작성
    @Transactional
    @PostMapping("/post")
    public ResponseEntity<ApiResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    // 글 조회(전체)
    @GetMapping ("/post")
    public ResponseEntity <List<PostResponseDto>> showPosts () {
        return ResponseEntity.status(HttpStatus.OK).body(postService.showPosts());
    }

    // 글 조회 (단건)
    @GetMapping("/post/{id}")
    public PostResponseDto showPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return postService.showPost(id, userDetails.getUser());
    }

    // 글 수정 (단건)
    @Transactional
    @PutMapping("/post/{id}")
    public ResponseEntity<ApiResponseDto> editPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.editPost(id, postRequestDto, userDetails.getUser());
    }

    // 글 삭제
    @Transactional
    @DeleteMapping("/post/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getUser());
    }
}
