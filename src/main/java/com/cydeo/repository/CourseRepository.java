package com.cydeo.repository;

import com.cydeo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    //find all courses by category
    List<Course> findByCategory(String category);

    //Find all courses by Category and order the entities by name
    List<Course> findByCategoryByName(String category);

    //Checks if a course with the provided name exists. Return true if course exists, false otherwise
    boolean existsByName(String name);
}
