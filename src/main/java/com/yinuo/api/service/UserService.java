package com.yinuo.api.service;

import com.yinuo.api.model.vo.UserRequest;
import com.yinuo.api.repository.dao.RoleDao;
import com.yinuo.api.repository.dao.UserDao;
import com.yinuo.api.repository.po.Roles;
import com.yinuo.api.repository.po.Users;
import com.yinuo.api.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
@Service
public class UserService {
    static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Transactional
    public Boolean exists(String username) {
        return userDao.count(username) > 0;
    }

    @Transactional
    public Boolean existsId(Long id) {
        return userDao.countById(id) > 0;
    }

    @Transactional
    public long add(UserRequest userRequest) {
        final String passwordHash = new BCryptPasswordEncoder().encode(userRequest.getPassword());
        final long uid = IdUtil.generateId();
        userDao.insert(new Users(uid, userRequest.getUsername(), passwordHash, 1, new Date()));
        roleDao.insert(new Roles(uid, userRequest.getRole()));
        return uid;
    }

    @Transactional
    public Boolean existsRole(Long uid, String authority) {
        return roleDao.count(new Roles(uid, authority)) > 0;
    }

    @Transactional
    public Boolean authenticate(String username, String password) {
        final String storedPasswordHash = userDao.selectPasswordHashByUsername(username);
        if (storedPasswordHash == null) {
            return false;
        }
        return new BCryptPasswordEncoder().matches(password, storedPasswordHash);
    }

    @Transactional
    public List<String> findAuthorities(String username) {
        return userDao.selectAuthoritiesByUsername(username);
    }

    @Transactional
    public Long findId(String username) {
        return userDao.selectIdByUsername(username);
    }
}
