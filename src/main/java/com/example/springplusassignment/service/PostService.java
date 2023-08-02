package com.example.springplusassignment.service;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.PostRequestDto;
import com.example.springplusassignment.dto.PostResponseDto;
import com.example.springplusassignment.entity.Post;
import com.example.springplusassignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public ResponseEntity<ApiResponseDto> createPost(PostRequestDto postRequestDto) {
        String title = postRequestDto.getTitle();
        String contents = postRequestDto.getContents();

        if (title == null) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "제목을 작성해주세요."));
        } else if (contents == null) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "글 내용을 작성해주세요"));
        }

        postRepository.save(new Post(title, contents));
        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK, "게시글 작성 완료"));
    }

    public List<PostResponseDto> showPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        return postList.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }
}
