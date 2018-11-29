package ${package}.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ${package}.repository.UserRepository;
import ${package}.repository.domain.UserPO;
import ${package}.repository.exception.UserNotFoundDBException;
import ${package}.service.UserService;
import ${package}.service.converter.UserBOConverter;
import ${package}.service.domain.UserBO;
import ${package}.service.exception.UserNotFoundServiceException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final UserBOConverter converter;

    public UserServiceImpl(UserRepository repository, UserBOConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public UserBO findById(Long id) throws UserNotFoundServiceException {
        logger.info("Get user by id={}", id);
        try {
            UserPO userPO = repository.findById(id);
            return converter.toUserBO(userPO);
        } catch (UserNotFoundDBException e) {
            logger.error(e.getMessage(), e);
            throw new UserNotFoundServiceException(e.getMessage());
        }
    }

    @Override
    public UserBO findByLogin(String login) throws UserNotFoundServiceException {
        logger.info("Get user by login={}", login);
        try {
            UserPO userPO = repository.findByLogin(login);
            return converter.toUserBO(userPO);
        } catch (UserNotFoundDBException e) {
            logger.error(e.getMessage(), e);
            throw new UserNotFoundServiceException(e.getMessage());
        }
    }

    @Override
    public UserBO findByEmail(String email) throws UserNotFoundServiceException {
        logger.info("Get user by email={}", email);
        try {
            UserPO userPO = repository.findByEmail(email);
            return converter.toUserBO(userPO);
        } catch (UserNotFoundDBException e) {
            logger.error(e.getMessage(), e);
            throw new UserNotFoundServiceException(e.getMessage());
        }
    }

    @Override
    public UserBO save(UserBO user) {
        logger.info("Trying to save user {}", user);
        UserPO po = converter.toUserPO(user);
        UserPO saved = repository.save(po);
        return converter.toUserBO(saved);
    }

    @Override
    public void delete(Long userId) {
        logger.info("Deleting user by id={}", userId);
        repository.delete(userId);
    }

    @Override
    public List<UserBO> findAll() {
        logger.info("Retrieving all users");
        List<UserPO> list = repository.findAll();
        logger.info("{} users were found", list.size());
        return converter.toUserBOList(list);
    }
}
