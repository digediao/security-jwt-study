package com.zzz.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 9:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    private String userName;
    private String password;

    private String nickName;
}
