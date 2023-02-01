package com.example.kassa3.service.abstracts;

import com.example.kassa3.model.entity.Phone;
import com.example.kassa3.model.entity.User;

public interface UserService extends ReadWriteService<User, Long>{
    User findByUsername(String username);

    User findByPhone(Phone phone);

    public boolean existsByUsername(String username);

    public boolean existsByPhone(Phone phone);

    User findByConfirmationToken(String token);
}
