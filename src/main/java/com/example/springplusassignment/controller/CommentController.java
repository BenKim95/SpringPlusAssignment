package com.example.springplusassignment.controller;

import com.example.springplusassignment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;


}
