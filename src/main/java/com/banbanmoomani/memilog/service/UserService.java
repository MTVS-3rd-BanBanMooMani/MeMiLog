package com.banbanmoomani.memilog.service;

import com.banbanmoomani.memilog.DAO.UserMapper;
import com.banbanmoomani.memilog.DTO.SignUpRequestDTO;
import com.banbanmoomani.memilog.DTO.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean duplicateCheck(SignUpRequestDTO signUpRequestDTO) {
        List<UserDTO> userList = userMapper.findAllUser();

        boolean duplicate = false;
        for (UserDTO user : userList) {
            if(signUpRequestDTO.getEmail() == user.getEmail()) {
                duplicate = true;
                break;
            }
        }

        return duplicate;
    }

    @Transactional
    public void insertUser(SignUpRequestDTO signUpRequestDTO) {
        // 현재 가입 시간 설정
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(signUpRequestDTO.getEmail()); // 이메일 설정
        userDTO.setPassword(signUpRequestDTO.getPassword()); // 비밀번호 설정
        String birthday = signUpRequestDTO.getYear() + "-" + signUpRequestDTO.getMonth() + "-" + signUpRequestDTO.getDay();
        userDTO.setBirthday(java.sql.Date.valueOf(birthday)); // 생일 설정
        userDTO.setNickName(signUpRequestDTO.getNickname()); // 닉네임 설정
        userDTO.setGender(signUpRequestDTO.getGender()); // 성별 설정
        userDTO.setCaution_weights(0);
        userDTO.setSignup_date(new java.sql.Date(System.currentTimeMillis())); // 가입일 설정
        userMapper.insertUser(userDTO);
    }
}
