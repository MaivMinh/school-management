package com.foolish.schoolmanagement.DTOs;

import java.sql.Date;

public record RegistrationsDTO(
        int courseId,
        String name,
        double grade,
        double rating,
        int fee,
        Date begin,
        Date end
) {

}
