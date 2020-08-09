package com.todotresde.interbanking.usermanagement.service;

import com.todotresde.interbanking.usermanagement.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);
}
