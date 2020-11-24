package com.jlq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/24 15:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String username;
    private String password;
    private String newPassword;
}


