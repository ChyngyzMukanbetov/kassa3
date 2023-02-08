package com.example.kassa3.service.impl;

import com.example.kassa3.dao.abstracts.UserDao;
import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.entity.User;
import com.example.kassa3.service.abstracts.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User, Long> implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    public User findByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User findByPhone(Phone phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {return userDao.existsByEmail(email); }

    @Override
    public boolean existsByPhone(Phone phone) {
        return userDao.existsByPhone(phone);
    }

    @Override
    public User findByConfirmationToken(String token) {
        return userDao.getUserByConfirmationToken(token);
    }
}
