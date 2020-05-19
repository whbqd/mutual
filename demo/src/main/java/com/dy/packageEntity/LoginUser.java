package com.dy.packageEntity;

import com.dy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ZT
 * Date: 2020/5/19 20:30
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private User data;
    private String token;
    private boolean msg;
}
