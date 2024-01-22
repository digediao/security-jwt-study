package com.zzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzz.domain.SysMenu;

import java.util.List;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/22 12:41
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermissionByUserId(Long id);
}
