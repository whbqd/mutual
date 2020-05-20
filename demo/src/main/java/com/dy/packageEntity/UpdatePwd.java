package com.dy.packageEntity;

import com.dy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ZT
 * Date: 2020/5/20 10:33
 * Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePwd {
    private User data;
    private boolean msg;
}
