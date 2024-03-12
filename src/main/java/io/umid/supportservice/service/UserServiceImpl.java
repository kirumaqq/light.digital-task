package io.umid.supportservice.service;

import io.umid.supportservice.dto.EditUserDto;
import io.umid.supportservice.dto.GetUserDto;
import io.umid.supportservice.exception.ResourceNotFoundException;
import io.umid.supportservice.mapper.UserMapper;
import io.umid.supportservice.repository.RoleRepository;
import io.umid.supportservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;


    @Transactional
    @Override
    public GetUserDto addRoleToUser(EditUserDto editUserDto) {

        log.debug("Searching user by its id: {}", editUserDto.id());

        var user = userRepository.findById(editUserDto.id()).orElseThrow(
                () -> new ResourceNotFoundException("No user with such id " + editUserDto.id())
        );

        log.debug("Retrieving role by its name: {}", editUserDto.role());
        var role = roleRepository.findByName(editUserDto.role()).orElseThrow(
                () -> new ResourceNotFoundException("No role with such name " + editUserDto.role())
        );

        user.addRole(role);

        return userMapper.toDto(user);
    }
}
