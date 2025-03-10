package model.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record UserResponseDto(
        String uuid,
        String userName,
        String email,
        Date createdDate,
        String profile
) {
}
