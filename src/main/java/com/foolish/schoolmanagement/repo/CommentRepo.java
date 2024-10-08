package com.foolish.schoolmanagement.repo;

import com.foolish.schoolmanagement.model.Comment;
import com.foolish.schoolmanagement.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
  List<Comment> findAllByCourse(Courses courses);
  Comment findAllById(Integer id);

  List<Comment> findAllByParentComment(Comment parent);
}
