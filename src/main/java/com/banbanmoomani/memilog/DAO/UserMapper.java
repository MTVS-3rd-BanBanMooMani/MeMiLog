package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.ModifyRequestDTO;
import com.banbanmoomani.memilog.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO findUserByEmail(UserDTO userDTO);
    List<UserDTO> findAllUser();
    void insertUser(UserDTO userDTO);
    void updateUserByEmail(ModifyRequestDTO modifyRequestDTO);
    void deleteUserByEmail(ModifyRequestDTO modifyRequestDTO);
}
