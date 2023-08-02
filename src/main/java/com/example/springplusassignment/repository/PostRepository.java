package com.example.springplusassignment.repository;

import com.example.springplusassignment.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}
