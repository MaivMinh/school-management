package com.foolish.schoolmanagement.DTOs;

import com.foolish.schoolmanagement.model.Roles;

import java.util.List;

public record UserDTO(
        int userId,
        String name,
        String mobileNum,
        String email,
        String img,
        List<Roles> roles,
        int classId,
        String className
) {
}
