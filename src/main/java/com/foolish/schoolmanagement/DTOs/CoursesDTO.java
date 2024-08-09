package com.foolish.schoolmanagement.DTOs;

import java.sql.Date;

public record CoursesDTO(
        int courseId,
        String name,
        int fee,
        String category,
        int capacity,
        int attendees,
        String img,
        Date begin,
        Date end,
        String introduction,
        String description,
        int lessons,
        double vote,
        String state
) {
}