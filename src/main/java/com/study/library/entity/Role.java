package com.study.library.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    private int roleId;
    private String roleName;
    private String RoleNameKor;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
