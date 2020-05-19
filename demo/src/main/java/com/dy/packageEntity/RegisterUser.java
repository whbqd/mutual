package com.dy.packageEntity;

import com.dy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ZT
 * Date: 2020/5/19 20:59
 * Version 1.0
 * @author 18086
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    private User data;
    private boolean repeat;
    private boolean msg;
}
