package ${package}.repository.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ${package}.repository.UserJpaRepository;
import ${package}.repository.converter.UserEntityConverter;
import ${package}.repository.domain.UserEntity;
import ${package}.repository.domain.UserPO;
import ${package}.repository.exception.UserNotFoundDBException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryImplTest {
    private static final long USER_ID = 1234567L;

    @InjectMocks
    private UserRepositoryImpl repository;

    @Mock
    private UserJpaRepository jpaRepository;

    @Mock
    private UserEntityConverter converter;
    private UserEntity entity;
    private UserPO po;

    @Before
    public void setUp() throws Exception {
        entity = mock(UserEntity.class);
        po = mock(UserPO.class);
    }

    @Test
    public void findById() throws UserNotFoundDBException {
        long id = 1234567L;

        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(converter.toUserPO(entity)).thenReturn(po);

        UserPO actual = repository.findById(id);

        assertThat(actual, is(po));

        verify(jpaRepository, times(1)).findById(id);
        verify(converter, times(1)).toUserPO(entity);
    }

    @Test(expected = UserNotFoundDBException.class)
    public void findByIdNotFoundException() throws UserNotFoundDBException {

        when(jpaRepository.findById(USER_ID)).thenReturn(Optional.empty());

        repository.findById(USER_ID);
    }

    @Test
    public void findByLogin() throws UserNotFoundDBException {
        String login = "userLogin";

        when(jpaRepository.findByLogin(login)).thenReturn(Optional.of(entity));
        when(converter.toUserPO(entity)).thenReturn(po);

        UserPO actual = repository.findByLogin(login);

        assertThat(actual, is(po));

        verify(jpaRepository, times(1)).findByLogin(login);
        verify(converter, times(1)).toUserPO(entity);
    }

    @Test(expected = UserNotFoundDBException.class)
    public void findByLoginNotFoundException() throws UserNotFoundDBException {
        String login = "userLogin";

        when(jpaRepository.findByLogin(login)).thenReturn(Optional.empty());

        repository.findByLogin(login);
    }

    @Test
    public void findByEmail() throws UserNotFoundDBException {
        String email = "spetrychenko@javatar.pro";

        when(jpaRepository.findByEmail(email)).thenReturn(Optional.of(entity));
        when(converter.toUserPO(entity)).thenReturn(po);

        UserPO actual = repository.findByEmail(email);

        assertThat(actual, is(po));

        verify(jpaRepository, times(1)).findByEmail(email);
        verify(converter, times(1)).toUserPO(entity);
    }

    @Test(expected = UserNotFoundDBException.class)
    public void findByEmailUserNotFoundException() throws UserNotFoundDBException {
        String email = "spetrychenko@javatar.pro";

        when(jpaRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(converter.toUserPO(entity)).thenReturn(po);

        repository.findByEmail(email);
    }

    @Test
    public void save() {

        when(converter.toUserEntity(po)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(entity);
        when(converter.toUserPO(entity)).thenReturn(po);

        UserPO actual = repository.save(po);

        assertThat(actual, is(po));

        verify(converter, times(1)).toUserEntity(po);
        verify(jpaRepository, times(1)).save(entity);
        verify(converter, times(1)).toUserPO(entity);
    }

    @Test
    public void delete() {

        doNothing().when(jpaRepository).deleteById(USER_ID);

        repository.delete(USER_ID);

        verify(jpaRepository, times(1)).deleteById(USER_ID);
    }

    @Test
    public void findAll() {

        List<UserEntity> entities = Collections.singletonList(entity);
        when(jpaRepository.findAll()).thenReturn(entities);
        when(converter.toUserPOList(entities)).thenReturn(Collections.singletonList(po));

        List<UserPO> list = repository.findAll();

        assertThat(list, hasSize(1));
        assertThat(list.get(0), is(po));

        verify(jpaRepository, times(1)).findAll();
        verify(converter, times(1)).toUserPOList(entities);
    }
}