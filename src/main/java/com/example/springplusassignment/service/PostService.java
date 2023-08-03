package com.example.springplusassignment.service;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.PostRequestDto;
import com.example.springplusassignment.dto.PostResponseDto;
import com.example.springplusassignment.entity.Post;
import com.example.springplusassignment.entity.User;
import com.example.springplusassignment.jwt.JwtUtil;
import com.example.springplusassignment.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    // Post 작성
    public ResponseEntity<ApiResponseDto> createPost(PostRequestDto postRequestDto, User user) {
        String title = postRequestDto.getTitle();
        String contents = postRequestDto.getContents();


        if (title == null) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "제목을 작성해주세요."));
        } else if (contents == null) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "글 내용을 작성해주세요"));
        } else if (user == null) {
                return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "사용자 권한이 없습니다."));
        }

        postRepository.save(new Post(title, contents, user));
        return ResponseEntity.status(201).body(new ApiResponseDto(HttpStatus.CREATED, "게시글 작성 완료"));
    }

    //Post 전체 조회
    public List<PostResponseDto> showPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        return postList.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    // Post 단건 조회
    public PostResponseDto showPost(Long id) {
       Optional <Post> post = postRepository.findById(id);
       if (post.isEmpty()) {
           throw new IllegalArgumentException("Post가 존재하지 않습니다.");
       }
        return new PostResponseDto(post.get());
    }

    // Post 수정
    public ResponseEntity<ApiResponseDto> editPost(Long id, PostRequestDto postRequestDto, User user) {
        Optional <Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "선택한 게시글은 존재하지 않습니다."));
        } else if (!Objects.equals(post.get().getUser().getUsername(),user.getUsername())) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "게시글 작성자만 수정할 수 있습니다."));
        }
        post.get().setTitle(postRequestDto.getTitle());
        post.get().setContents(postRequestDto.getContents());
        postRepository.save(post.get());
        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK, "게시글 변경 완료"));
    }

    // Post 삭제
    public ResponseEntity<ApiResponseDto> deletePost(Long id, User user) {
       Optional<Post> post = postRepository.findById(id);

       if (post.isEmpty()) {
           return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "선택한 게시글은 존재하지 않습니다."));
       } else if (!Objects.equals(post.get().getUser().getUsername(),user.getUsername())) {
           // !Object.equals 사용하여 1. id에 해당되는 Optional Post 정보 get 2.해당 Post 작성한 User 정보 get 3. User의 Username get 4.username과 로그인한 user의 username 일치하는지 확인
           return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "작성자만 게시글을 삭제할 수 있습니다."));
       }
       postRepository.delete(post.get());
        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK, "게시글 삭제 완료"));
    }


}
