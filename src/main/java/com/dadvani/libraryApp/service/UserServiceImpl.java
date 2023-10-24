package com.dadvani.libraryApp.service;

import com.dadvani.libraryApp.dto.UserDto;
import com.dadvani.libraryApp.exceptions.PatronWasNotFoundException;
import com.dadvani.libraryApp.models.User;
import com.dadvani.libraryApp.repository.UserRepository;
import com.dadvani.libraryApp.service.Iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        userDto.setRole("USER");
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getFullname(),userDto.getRole(), userDto.getPhoneNumber(), userDto.isMembershipStatus());
        return userRepository.save(user);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public User get(Integer id) throws PatronWasNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PatronWasNotFoundException("can't find book id");

    }

    @Override
    public void delete(Integer id) {
        Integer count = userRepository.countById(id);
        if (count == null || count == 0) {
        }
        userRepository.deleteById(id);
    }

    @Override
    public User saveB(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isMemberShipStatus(UserDto userDto) {
        return userDto.isMembershipStatus();
    }
}
