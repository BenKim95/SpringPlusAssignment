package com.example.springplusassignment.repository;

import com.example.springplusassignment.entity.Comment;
import com.example.springplusassignment.entity.Post;
import com.example.springplusassignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findAllByUser(User user);
//    List<Comment> findAllByPost(List<Post> postlist); // Post 전체 조회할때 List형식으로 댓글불러올거면 사용
}
