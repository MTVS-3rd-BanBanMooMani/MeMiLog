package com.banbanmoomani.memilog.service.user;

import com.banbanmoomani.memilog.DAO.UserMapper;
import com.banbanmoomani.memilog.DTO.user.ModifyRequestDTO;
import com.banbanmoomani.memilog.DTO.user.SignUpRequestDTO;
import com.banbanmoomani.memilog.DTO.user.UserDTO;
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
        userDTO.setNickname(signUpRequestDTO.getNickname()); // 닉네임 설정
        userDTO.setGender(signUpRequestDTO.getGender()); // 성별 설정
        userDTO.setCaution_weights(0);
        userDTO.setSignup_date(new java.sql.Date(System.currentTimeMillis())); // 가입일 설정
        userMapper.insertUser(userDTO);
    }

    public UserDTO findUserById(int user_id) {
        return userMapper.findUserById(user_id);
    }

    public UserDTO findByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Transactional
    public ModifyRequestDTO updateUser(UserDTO userDTO) {
        ModifyRequestDTO modifyRequestDTO = convertToModifyRequestDTO(userDTO);
        userMapper.updateUserByEmail(userDTO);
        return modifyRequestDTO;
    }

    @Transactional
    public void deleteUser(UserDTO userDTO) {
        userMapper.deleteUserByEmail(userDTO);
    }

    private static ModifyRequestDTO convertToModifyRequestDTO(UserDTO userDTO) {
        if(userDTO == null) {
            return null;
        }

        String email = userDTO.getEmail();
        String modify_nickname = userDTO.getNickname();
        String modify_password = userDTO.getPassword();

        return new ModifyRequestDTO(email, modify_nickname, modify_password);
    }
}
