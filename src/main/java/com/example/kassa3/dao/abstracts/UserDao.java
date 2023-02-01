package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.entity.User;

public interface UserDao extends ReadWriteDao<User, Long>{
    User getUserByUsername(String username);

    User getUserByPhone(Phone phone);

    public boolean existsByUsername(String username);

    public boolean existsByPhone(Phone phone);

    User getUserByConfirmationToken(String token);
}
