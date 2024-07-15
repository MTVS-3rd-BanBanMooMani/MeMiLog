package com.banbanmoomani.memilog.DTO;

import java.util.List;

public class AdminDTO {

    // getBlackListDTO
    public record getBlackListDTO(
            List<BanUserDTO> banUserDTOList,
            List<BlackListUserDTO> blackListUserDTOList
    ) {
        public record BanUserDTO() {

        }

        public record BlackListUserDTO() {

        }
    }
}
