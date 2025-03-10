package model.service;

import mapper.UserMapper;
import model.dto.UserResponseDto;
import model.dto.UserUpdateDto;
import model.repository.UserRepository;
import model.service.abstraction.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepository();
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        userRepository.findAll().forEach(e->{
            userResponseDtoList.add(UserMapper.mapFromUserToUserResponseDto(e));
        });
        return userResponseDtoList;
    }

    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        return UserMapper.mapFromUserToUserResponseDto(userRepository.findUserByUuid(uuid));
    }

    @Override
    public int deleteUserByUuid(String uuid) {
        return 0;
    }

    @Override
    public UserResponseDto updateUserByUuid(String uuid, UserUpdateDto userUpdateDto) {
        return null;
    }
}
