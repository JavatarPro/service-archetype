package ${package}.repository;


import ${package}.repository.model.UserPO;
import ${package}.repository.exception.UserNotFoundDBException;

import java.util.List;

public interface UserRepository {
    UserPO findById(Long id) throws UserNotFoundDBException;
    UserPO findByLogin(String login) throws UserNotFoundDBException;
    UserPO findByEmail(String email) throws UserNotFoundDBException;
    UserPO save(UserPO user);
    List<UserPO> findAll();
    void delete(Long userId);
}