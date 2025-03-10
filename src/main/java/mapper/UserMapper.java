package mapper;

import model.dto.UserResponseDto;
import model.entity.User;

public class UserMapper {
    public static UserResponseDto mapFromUserToUserResponseDto(User user){
        return UserResponseDto.builder()
                .uuid(user.getUuid())
                .userName(user.getUserName())
                .email(user.getEmail())
                .profile(user.getProfile())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
