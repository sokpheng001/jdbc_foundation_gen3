package model.service.abstraction;

import model.dto.UserResponseDto;
import model.dto.UserUpdateDto;


import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUuid(String uuid);
    int deleteUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid, UserUpdateDto userUpdateDto);
}
