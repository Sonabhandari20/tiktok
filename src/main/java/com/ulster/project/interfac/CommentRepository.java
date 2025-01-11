package com.ulster.project.interfac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ulster.project.models.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> 
 {
 }
