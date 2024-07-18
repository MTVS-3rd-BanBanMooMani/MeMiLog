package com.banbanmoomani.memilog.DAO;

import com.banbanmoomani.memilog.DTO.ModifyRequestDTO;
import com.banbanmoomani.memilog.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO findUserById(@Param("user_id") int user_id);
    UserDTO findUserByEmail(@Param("email") String email);
    List<UserDTO> findAllUser();
    void insertUser(UserDTO userDTO);
    void updateUserByEmail(UserDTO userDTO);
    void deleteUserByEmail(UserDTO userDTO);
}
