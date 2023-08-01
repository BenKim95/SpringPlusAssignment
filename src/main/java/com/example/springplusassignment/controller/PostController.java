package com.example.springplusassignment.controller;

import com.example.springplusassignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;


}
