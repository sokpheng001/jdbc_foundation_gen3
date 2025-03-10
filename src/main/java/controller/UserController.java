package controller;

import model.dto.UserResponseDto;
import model.service.UserServiceImpl;
import model.service.abstraction.UserService;

import java.util.List;

public class UserController {
    private final UserService userService = new UserServiceImpl();
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }
    public UserResponseDto getUserByUuid(String uuid){
        return userService.getUserByUuid(uuid);
    }
}
