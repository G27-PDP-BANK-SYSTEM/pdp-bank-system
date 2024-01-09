package org.example.pdpbanksystem.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.pdpbanksystem.accNumber.AccountNumberService;
import org.example.pdpbanksystem.accNumber.dto.AccountNumberResponseDto;
import org.example.pdpbanksystem.accNumber.entity.AccountNumber;
import org.example.pdpbanksystem.common.service.GenericCrudService;
import org.example.pdpbanksystem.jwt.JwtService;
import org.example.pdpbanksystem.user.dto.*;
import org.example.pdpbanksystem.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class UserService extends GenericCrudService<User, Integer, UserCreateDto, UserUpdateDto,
        UserPatchDto, UserResponseDto> implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapperDto mapper;
    private final JwtService jwtService;
    private final Class<User> EntityClass = User.class;
    private final PasswordEncoder passwordEncoder;

    private final AccountNumberService accountNumberService;
    ModelMapper modelMapper = new ModelMapper();
    @Override
    protected User save(UserCreateDto userCreateDto) {
        User entity = mapper.toEntity(userCreateDto);
        entity.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));

        AccountNumberResponseDto accountNumberResponseDto = accountNumberService.create();
        AccountNumber number = modelMapper
                .map(accountNumberResponseDto, AccountNumber.class);
        entity.setAccountNumber(number);

        return repository.save(entity);
    }
    public UserSignInResponseDto signIn(UserSignInDto signInDto) {
        String userEmail = signInDto.getEmail();

        User user = repository.findUserByEmail(userEmail).orElseThrow(
                () -> new BadCredentialsException("Email or password doesn't match"));

        if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {

            String token = jwtService.generateToken(user.getPhoneNumber());
            UserResponseDto responseDto = mapper.toResponseDto(user);
            return new UserSignInResponseDto(responseDto, token);
        }
        throw new BadCredentialsException("Email or password doesn't match");


    }

    @Override
    protected User updateEntity(UserUpdateDto userUpdateDto, User user) {
        mapper.update(userUpdateDto, user);
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("bad credentials"));
    }

}
