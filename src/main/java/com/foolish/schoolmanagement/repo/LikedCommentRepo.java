package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.LikedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedCommentRepo extends JpaRepository<LikedComment, Integer> {
}
