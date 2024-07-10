package com.ladmakhi.projecttracker.features.users;

import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;
import com.ladmakhi.projecttracker.features.users.dtos.GetUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public GetUserResponseDto createUser(CreateUserDto dto) throws Exception {
        boolean isDuplicateByUsernameOrEmail = userRepository.existsByEmailOrUsername(dto.email(), dto.username());
        if (isDuplicateByUsernameOrEmail) {
            throw new Exception("Duplicated By Email or Username");
        }
        User user = new User();
        user.setEmail(dto.email());
        user.setStatus(UserStatus.NOT_VERIFIED);
        user.setUsername(dto.username());
        user.setProfileUrl(dto.profileUrl());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setLastStatusUpdateDate(LocalDate.now());
        User savedUser = userRepository.save(user);
        GetUserResponseDto responseDto = new GetUserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getProfileUrl(),
                savedUser.getStatus(),
                savedUser.getLastStatusUpdateDate()
        );
        return responseDto;
    }

    @Override
    public GetUserResponseDto findUserByEmailAndPassword(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User Is Not Found");
        }
        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
        if (!isPasswordMatch) {
            throw new Exception("Password Is Not Match With Email");
        }
        GetUserResponseDto dto = new GetUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getProfileUrl(),
                user.getStatus(),
                user.getLastStatusUpdateDate()
        );
        return dto;
    }

    @Override
    public GetUserResponseDto changeStatusOfUser(Long id, UserStatus status) throws Exception {
        User user = findUserById(id);
        user.setStatus(status);
        user.setLastStatusUpdateDate(LocalDate.now());
        User savedUser = userRepository.save(user);
        GetUserResponseDto dto = new GetUserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getProfileUrl(),
                savedUser.getStatus(),
                savedUser.getLastStatusUpdateDate()
        );
        return dto;
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User Is Not Found"));
    }
}
