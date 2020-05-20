package com.dy.packageEntity;

import com.dy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ZT
 * Date: 2020/5/20 10:29
 * Version 1.0
 * @author 18086
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckingUser {
    private User data;
    private boolean msg;
}
