package model.dto;

import lombok.Builder;

@Builder
public record UserUpdateDto(
        String userName,
        String profile
) {
}
