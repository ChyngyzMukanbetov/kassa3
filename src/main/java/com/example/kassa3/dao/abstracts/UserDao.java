package com.example.kassa3.dao.abstracts;

import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.entity.User;

public interface UserDao extends ReadWriteDao<User, Long>{
    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User getUserByPhone(Phone phone);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhone(Phone phone);

    User getUserByConfirmationToken(String token);


}
