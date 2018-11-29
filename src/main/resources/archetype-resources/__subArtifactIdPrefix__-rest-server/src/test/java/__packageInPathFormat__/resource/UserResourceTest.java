package ${package}.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.javatar.commons.reader.JsonReader;
import pro.javatar.commons.reader.YamlReader;
import ${package}.converter.UserTOConverter;
import ${package}.domain.UserTO;
import ${package}.exception.ExceptionAdvisor;
import ${package}.service.UserService;
import ${package}.service.domain.UserBO;
import ${package}.service.exception.UserNotFoundServiceException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest {
    private static final String USER_ID = "1234567";
    private static final Long ID = Long.valueOf(USER_ID);

    private MockMvc mockMvc;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService userService;

    @Mock
    private UserTOConverter converter;
    private UserBO bo;
    private UserTO to;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                          .standaloneSetup(resource)
                          .setControllerAdvice(new ExceptionAdvisor())
                          .setMessageConverters(new MappingJackson2HttpMessageConverter())
                          .build();

        bo = readYml(UserBO.class, "user-bo.yml");
        to = readYml(UserTO.class, "user-to.yml");
    }

    @Test
    public void create() throws Exception {

        when(converter.toUserBO(to)).thenReturn(bo);
        when(userService.save(bo)).thenReturn(bo);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .post("/users")
                                                      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                                      .content(serialize(to)))
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.CREATED.value()))
                                      .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        assertThat(location, is("/users/" + USER_ID));

        verify(converter, times(1)).toUserBO(to);
        verify(userService, times(1)).save(bo);
    }

    @Test
    public void update() throws Exception {
        when(converter.toUserBO(to)).thenReturn(bo);
        when(userService.save(bo)).thenReturn(bo);
        when(converter.toUserTO(bo)).thenReturn(to);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .put("/users/{id}", USER_ID)
                                                      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                                      .content(serialize(to)))
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.ACCEPTED.value()))
                                      .andReturn();

        verify(converter, times(1)).toUserBO(to);
        verify(userService, times(1)).save(bo);
    }

    @Test
    public void delete() throws Exception {

        doNothing().when(userService).delete(ID);

        mockMvc.perform(MockMvcRequestBuilders
                                .delete("/users/{id}", USER_ID)
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .content(serialize(to)))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();

        verify(userService, times(1)).delete(ID);
    }

    @Test
    public void search() throws Exception {
        List<UserBO> users = Collections.singletonList(bo);
        List<UserTO> list = Collections.singletonList(to);

        when(userService.findAll()).thenReturn(users);
        when(converter.toUserTOList(users)).thenReturn(list);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .get("/users/search")
                                                      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.OK.value()))
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                                      .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        UserTO[] actual = deserialize(content, UserTO[].class);

        assertThat(Arrays.asList(actual), hasSize(1));
        assertThat(actual[0], is(to));

        verify(userService, times(1)).findAll();
        verify(converter, times(1)).toUserTOList(users);
    }

    @Test
    public void getById() throws Exception {
        when(userService.findById(ID)).thenReturn(bo);
        when(converter.toUserTO(bo)).thenReturn(to);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .get("/users/{id}", USER_ID)
                                                      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.OK.value()))
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                                      .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        UserTO actual = deserialize(content, UserTO.class);

        assertThat(actual, is(to));

        verify(userService, times(1)).findById(ID);
        verify(converter, times(1)).toUserTO(bo);
    }

    @Test
    public void getByIdNotFoundException() throws Exception {
        when(userService.findById(ID)).thenThrow(UserNotFoundServiceException.class);

        mockMvc.perform(MockMvcRequestBuilders
                                .get("/users/{id}", USER_ID)
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        verify(userService, times(1)).findById(ID);
    }

    @Test
    public void getByLogin() throws Exception {
        String userLogin = "user_login";

        when(userService.findByLogin(userLogin)).thenReturn(bo);
        when(converter.toUserTO(bo)).thenReturn(to);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .get("/users/login/{login}", userLogin)
                                                      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.OK.value()))
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                                      .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        UserTO actual = deserialize(content, UserTO.class);

        assertThat(actual, is(to));

        verify(userService, times(1)).findByLogin(userLogin);
        verify(converter, times(1)).toUserTO(bo);
    }

    @Test
    public void getByLoginNotFoundException() throws Exception {
        String userLogin = "user_login";

        when(userService.findByLogin(userLogin)).thenThrow(UserNotFoundServiceException.class);

        mockMvc.perform(MockMvcRequestBuilders
                                .get("/users/login/{login}", userLogin)
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        verify(userService, times(1)).findByLogin(userLogin);
    }

    @Test
    public void getByEmail() throws Exception {
        String email = "spetrychenko@javatar.pro";

        when(userService.findByEmail(email)).thenReturn(bo);
        when(converter.toUserTO(bo)).thenReturn(to);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .get("/users/emails/{email}", email)
                                                      .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.OK.value()))
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                                      .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        UserTO actual = deserialize(content, UserTO.class);

        assertThat(actual, is(to));

        verify(userService, times(1)).findByEmail(email);
        verify(converter, times(1)).toUserTO(bo);
    }

    @Test
    public void getByEmailNotFoundException() throws Exception {
        String email = "spetrychenko@javatar.pro";

        when(userService.findByEmail(email)).thenThrow(UserNotFoundServiceException.class);

        mockMvc.perform(MockMvcRequestBuilders
                                .get("/users/emails/{email}", email)
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        verify(userService, times(1)).findByEmail(email);
    }

    private <T> T readYml(Class<T> aClass, String fileName) {
        try {
            return YamlReader.getInstance().getObjectFromResource(UserTOConverter.class, fileName, aClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] serialize(Object obj) {
        try {
            return new ObjectMapper().writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't serialize object");
        }
    }

    private <T> T deserialize(String source, Class<T> tClass) {
        try {
            return JsonReader.getInstance().getObjectFromString(source, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't deserialize object", e);
        }
    }
}