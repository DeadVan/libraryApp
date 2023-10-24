package com.dadvani.libraryApp.service.Iservice;

import com.dadvani.libraryApp.dto.UserDto;
import com.dadvani.libraryApp.exceptions.PatronWasNotFoundException;
import com.dadvani.libraryApp.models.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);
    User save (UserDto userDto);
    List<User> findByRole(String role);
    User get(Integer id) throws PatronWasNotFoundException;
    void delete(Integer id);
    User saveB (User user);
    boolean isMemberShipStatus(UserDto userDto);
}
