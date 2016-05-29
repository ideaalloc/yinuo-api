package com.yinuo.api.repository.dao;

import com.yinuo.api.repository.po.Roles;

import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public interface RoleDao {
    int insert(Roles role);

    int count(Roles role);

    List<String> findAuthorities(Long uid);
}
