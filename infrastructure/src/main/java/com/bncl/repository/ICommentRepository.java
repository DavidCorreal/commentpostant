package com.bncl.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bncl.entity.Comment;


@Repository
public interface ICommentRepository extends JpaRepository<Comment, String> {

	public List<Comment> findByPostId(Integer postId);
	
}
