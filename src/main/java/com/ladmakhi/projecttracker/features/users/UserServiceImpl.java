package com.ladmakhi.projecttracker.features.users;

import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;
import com.ladmakhi.projecttracker.features.users.dtos.GetUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public GetUserResponseDto createUser(CreateUserDto dto) throws Exception {
        boolean isDuplicateByUsernameOrEmail = userRepository.existsByEmailOrUsername(dto.email(), dto.username());
        if (isDuplicateByUsernameOrEmail) {
            throw new Exception("Duplicated By Email or Username");
        }
        User user = User.builder()
                .email(dto.email())
                .status(UserStatus.NOT_VERIFIED)
                .username(dto.username())
                .profileUrl(dto.profileUrl())
                .password(passwordEncoder.encode(dto.password()))
                .lastStatusUpdateDate(LocalDate.now())
                .build();
        User savedUser = userRepository.save(user);
        return userMapper.convertUserToGetUserResponseDto(savedUser);
    }

    @Override
    public GetUserResponseDto findUserByEmailAndPassword(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User Is Not Found"));
        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
        if (!isPasswordMatch) {
            throw new Exception("Password Is Not Match With Email");
        }
        return userMapper.convertUserToGetUserResponseDto(user);
    }

    @Override
    public GetUserResponseDto changeStatusOfUser(Long id, UserStatus status) throws Exception {
        User user = findUserById(id);
        user.setStatus(status);
        user.setLastStatusUpdateDate(LocalDate.now());
        User savedUser = userRepository.save(user);
        return userMapper.convertUserToGetUserResponseDto(savedUser);
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User Is Not Found"));
    }

    @Override
    public User findByEmail(String email, boolean throwError) throws Exception {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> throwError ? new Exception("User is not found") : null);
    }
}
