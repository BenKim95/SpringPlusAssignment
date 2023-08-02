package com.example.springplusassignment.controller;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.PostRequestDto;
import com.example.springplusassignment.dto.PostResponseDto;
import com.example.springplusassignment.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class PostController {

    private final PostService postService;


    // 글 작성
    @PostMapping("/post")
    public ResponseEntity<ApiResponseDto> createPost (@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    // 글 조회(전체)
    @GetMapping ("/post")
    public List<PostResponseDto> showPosts () {
        return postService.showPosts();
    }
//
//    // 글 조회(단건)
//    @GetMapping("/post/{id}")
//    public ResponseEntity <PostResponseDto> showPost(@PathVariable Long id, ) {
//
//    }
}
