package com.ucsal.physicalSpaceManagement.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ucsal.physicalSpaceManagement.user.Enums.UserType;
import com.ucsal.physicalSpaceManagement.user.dto.UserDTO;
import com.ucsal.physicalSpaceManagement.user.entities.User;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO signup(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email já registrado.");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO signin(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha incorreta.");
        }
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(Optional<UserType> userType) {
        List<User> users;

        if (userType.isPresent()) {
            users = userRepository.findByUserType(userType.get());
        } else {
            users = userRepository.findAll();
        }

        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

}
