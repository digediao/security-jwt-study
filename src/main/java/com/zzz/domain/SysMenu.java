package com.zzz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/22 12:39
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_menu")
public class SysMenu {
    private Long menuId;
    private String menuName;
    private String perms;
}
