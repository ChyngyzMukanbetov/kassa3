package com.example.kassa3.dao.impl;

import com.example.kassa3.dao.abstracts.UserDao;
import com.example.kassa3.model.entity.Image;
import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends ReadWriteDaoImpl<User, Long> implements UserDao {

    public User getUserByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User getUserByPhone(Phone phone) {
        return em.createQuery(
                "SELECT User FROM User u JOIN u.phone WHERE u.phone.phoneCode = :phoneCode and u.phone.phoneNumber = :phoneNumber", User.class)
                .setParameter("phoneCode", phone.getPhoneCode()).setParameter("phoneNumber", phone.getPhoneNumber()).getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public boolean existsByUsername(String username) {
        return em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult() > 0;
    }

    public boolean existsByEmail(String email) {
        return em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }

    public boolean existsByPhone(Phone phone) {
        return em.createQuery("SELECT COUNT(u) FROM User u JOIN u.phone WHERE u.phone.phoneCode = :phoneCode and u.phone.phoneNumber = :phoneNumber", Long.class)
                .setParameter("phoneCode", phone.getPhoneCode()).setParameter("phoneNumber", phone.getPhoneNumber())
                .getSingleResult() > 0;
    }

    @Override
    public User getUserByConfirmationToken(String token) {
        return em.createQuery("SELECT u FROM User u WHERE u.confirmationToken = :token", User.class)
                .setParameter("token", token)
                .getSingleResult();
    }
}