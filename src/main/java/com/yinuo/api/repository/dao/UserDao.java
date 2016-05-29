package com.yinuo.api.repository.dao;

import com.yinuo.api.repository.po.Users;

import java.util.List;


/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-25
 */
public interface UserDao {
    int insert(Users user);

    int count(String username);

    int countById(Long id);

    String selectPasswordHashByUsername(String username);

    List<String> selectAuthoritiesByUsername(String username);

    Long selectIdByUsername(String username);
}
