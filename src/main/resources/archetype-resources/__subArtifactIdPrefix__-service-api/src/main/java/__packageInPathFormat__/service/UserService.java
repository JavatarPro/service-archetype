package ${package}.service;


import ${package}.service.domain.UserBO;
import ${package}.service.exception.UserNotFoundServiceException;

import java.util.List;

public interface UserService {
    UserBO findById(Long id) throws UserNotFoundServiceException;
    UserBO findByLogin(String login) throws UserNotFoundServiceException;
    UserBO findByEmail(String email) throws UserNotFoundServiceException;
    UserBO save(UserBO user);
    void delete(Long id);
    List<UserBO> findAll();
}