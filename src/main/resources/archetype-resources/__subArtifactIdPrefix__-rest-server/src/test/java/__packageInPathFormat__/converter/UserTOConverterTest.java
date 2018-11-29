package ${package}.converter;

import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pro.javatar.commons.reader.YamlReader;
import ${package}.domain.UserTO;
import ${package}.service.domain.UserBO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTOConverterTest {

    private UserTOConverter converter;
    private UserBO bo;
    private UserTO to;

    @Before
    public void setUp() throws Exception {
        converter = Mappers.getMapper(UserTOConverter.class);
        bo = readYml(UserBO.class, "user-bo.yml");
        to = readYml(UserTO.class, "user-to.yml");
    }

    @Test
    public void toUserTO() {
        UserTO actual = converter.toUserTO(bo);

        assertThat(actual, is(to));
    }

    @Test
    public void toUserTOList() {
        List<UserTO> actualList = converter.toUserTOList(Collections.singletonList(bo));

        assertThat(actualList, hasSize(1));
        assertThat(actualList.get(0), is(to));
    }

    @Test
    public void toUserBO() {
        UserBO actual = converter.toUserBO(to);

        assertThat(actual, is(bo));
    }

    private <T> T readYml(Class<T> aClass, String fileName) {
        try {
            return YamlReader.getInstance().getObjectFromResource(getClass(), fileName, aClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}