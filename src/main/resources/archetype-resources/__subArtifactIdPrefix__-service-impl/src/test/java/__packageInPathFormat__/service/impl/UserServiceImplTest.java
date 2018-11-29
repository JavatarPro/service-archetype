package ${package}.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ${package}.repository.UserRepository;
import ${package}.repository.domain.UserPO;
import ${package}.repository.exception.UserNotFoundDBException;
import ${package}.service.converter.UserBOConverter;
import ${package}.service.domain.UserBO;
import ${package}.service.exception.UserNotFoundServiceException;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final long USER_ID = 1234567L;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Mock
    private UserBOConverter converter;
    private UserPO po;
    private UserBO bo;

    @Before
    public void setUp() throws Exception {
        po = mock(UserPO.class);
        bo = mock(UserBO.class);
    }

    @Test
    public void findById() throws UserNotFoundDBException, UserNotFoundServiceException {

        when(repository.findById(USER_ID)).thenReturn(po);
        when(converter.toUserBO(po)).thenReturn(bo);

        UserBO actual = userService.findById(USER_ID);

        assertThat(actual, is(bo));

        verify(repository, times(1)).findById(USER_ID);
        verify(converter, times(1)).toUserBO(po);
    }

    @Test(expected = UserNotFoundServiceException.class)
    public void findByIdWithUserNotFoundServiceException() throws UserNotFoundDBException, UserNotFoundServiceException {
        long id = 1234567L;

        when(repository.findById(id)).thenThrow(UserNotFoundDBException.class);

        userService.findById(id);
    }

    @Test
    public void findByLogin() throws UserNotFoundDBException, UserNotFoundServiceException {
        String login = "user_login";

        when(repository.findByLogin(login)).thenReturn(po);
        when(converter.toUserBO(po)).thenReturn(bo);

        UserBO actual = userService.findByLogin(login);

        assertThat(actual, is(bo));

        verify(repository, times(1)).findByLogin(login);
        verify(converter, times(1)).toUserBO(po);
    }

    @Test(expected = UserNotFoundServiceException.class)
    public void findByLoginWithUserNotFoundServiceException() throws UserNotFoundDBException, UserNotFoundServiceException {
        String login = "user_login";

        when(repository.findByLogin(login)).thenThrow(UserNotFoundDBException.class);

        userService.findByLogin(login);
    }

    @Test
    public void findByEmail() throws UserNotFoundDBException, UserNotFoundServiceException {
        String email = "spetrychenko@javatar.pro";

        when(repository.findByEmail(email)).thenReturn(po);
        when(converter.toUserBO(po)).thenReturn(bo);

        UserBO actual = userService.findByEmail(email);

        assertThat(actual, is(bo));

        verify(repository, times(1)).findByEmail(email);
        verify(converter, times(1)).toUserBO(po);
    }

    @Test(expected = UserNotFoundServiceException.class)
    public void findByEmailWithUserNotFoundServiceException() throws UserNotFoundDBException, UserNotFoundServiceException {
        String email = "spetrychenko@javatar.pro";

        when(repository.findByEmail(email)).thenThrow(UserNotFoundDBException.class);

        userService.findByEmail(email);
    }

    @Test
    public void save() {
        when(converter.toUserPO(bo)).thenReturn(po);
        when(repository.save(po)).thenReturn(po);
        when(converter.toUserBO(po)).thenReturn(bo);

        UserBO actual = userService.save(bo);

        assertThat(actual, is(bo));

        verify(converter, times(1)).toUserPO(bo);
        verify(repository, times(1)).save(po);
        verify(converter, times(1)).toUserBO(po);
    }

    @Test
    public void delete() {
        doNothing().when(repository).delete(USER_ID);

        userService.delete(USER_ID);

        verify(repository, times(1)).delete(USER_ID);
    }

    @Test
    public void findAll() {
        List<UserPO> list = Collections.singletonList(po);
        when(repository.findAll()).thenReturn(list);
        when(converter.toUserBOList(list)).thenReturn(Collections.singletonList(bo));

        List<UserBO> actualList = userService.findAll();

        assertThat(actualList, hasSize(1));
        assertThat(actualList.get(0), is(bo));

        verify(repository, times(1)).findAll();
        verify(converter, times(1)).toUserBOList(list);
    }
}