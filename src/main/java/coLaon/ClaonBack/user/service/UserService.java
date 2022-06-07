package coLaon.ClaonBack.user.service;

import coLaon.ClaonBack.common.domain.enums.Address;
import coLaon.ClaonBack.common.exception.BadRequestException;
import coLaon.ClaonBack.common.exception.ErrorCode;
import coLaon.ClaonBack.common.validator.PasswordFormatValidator;
import coLaon.ClaonBack.common.validator.Validator;
import coLaon.ClaonBack.user.domain.User;
import coLaon.ClaonBack.user.dto.SignUpRequestDto;
import coLaon.ClaonBack.user.dto.UserResponseDto;
import coLaon.ClaonBack.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        this.userRepository.findByEmail(signUpRequestDto.getEmail()).ifPresent(
                email -> {
                    throw new BadRequestException(
                            ErrorCode.ROW_ALREADY_EXIST,
                            "이미 존재하는 EMAIL입니다."
                    );
                }
        );

        this.userRepository.findByNickname(signUpRequestDto.getNickname()).ifPresent(
                nickname -> {
                    throw new BadRequestException(
                            ErrorCode.ROW_ALREADY_EXIST,
                            "이미 존재하는 NICKNAME입니다."
                    );
                }
        );

        Address.validateAddress(signUpRequestDto.getWideActiveArea(), signUpRequestDto.getNarrowActiveArea());

        if (signUpRequestDto.getPassword() != null) {
            Validator validator = new PasswordFormatValidator(signUpRequestDto.getPassword());
            validator.validate();
        }

        return UserResponseDto.from(
                userRepository.save(
                        User.of(
                                signUpRequestDto.getPhoneNumber(),
                                signUpRequestDto.getEmail(),
                                signUpRequestDto.getPassword(),
                                signUpRequestDto.getNickname(),
                                signUpRequestDto.getWideActiveArea(),
                                signUpRequestDto.getNarrowActiveArea(),
                                signUpRequestDto.getImagePath(),
                                signUpRequestDto.getInstagramId()
                        )
                )
        );
    }
}

