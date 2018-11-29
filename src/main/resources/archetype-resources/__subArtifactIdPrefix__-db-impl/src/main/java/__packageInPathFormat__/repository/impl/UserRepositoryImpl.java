package ${package}.repository.impl;

import org.springframework.stereotype.Component;
import ${package}.repository.UserJpaRepository;
import ${package}.repository.UserRepository;
import ${package}.repository.domain.UserEntity;
import ${package}.repository.domain.UserPO;
import ${package}.repository.exception.UserNotFoundDBException;
import ${package}.repository.converter.UserEntityConverter;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;

    private final UserEntityConverter converter;

    public UserRepositoryImpl(UserJpaRepository repository, UserEntityConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public UserPO findById(Long id) throws UserNotFoundDBException {
        return repository.findById(id)
                       .map(converter::toUserPO)
                       .orElseThrow(() -> new UserNotFoundDBException("User with id=" + id + " not found"));
    }

    @Override
    public UserPO findByLogin(String login) throws UserNotFoundDBException {
        return repository.findByLogin(login)
                       .map(converter::toUserPO)
                       .orElseThrow(() -> new UserNotFoundDBException("User with login=" + login + " not found"));
    }

    @Override
    public UserPO findByEmail(String email) throws UserNotFoundDBException {
        return repository.findByEmail(email)
                       .map(converter::toUserPO)
                       .orElseThrow(() -> new UserNotFoundDBException("User with email=" + email + " not found"));
    }

    @Override
    public UserPO save(UserPO user) {
        UserEntity entity = converter.toUserEntity(user);
        UserEntity saved = repository.save(entity);
        return converter.toUserPO(saved);
    }

    @Override
    public List<UserPO> findAll() {
        List<UserEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return converter.toUserPOList(list);
    }

    @Override
    public void delete(Long userId) {
        repository.deleteById(userId);
    }
}
