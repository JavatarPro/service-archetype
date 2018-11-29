package ${package}.service.converter;

import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pro.javatar.commons.reader.YamlReader;
import ${package}.repository.domain.UserPO;
import ${package}.service.domain.UserBO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserBOConverterTest {

    private UserBOConverter converter;
    private UserBO bo;
    private UserPO po;

    @Before
    public void setUp() throws Exception {
        converter = Mappers.getMapper(UserBOConverter.class);
        bo = readYml(UserBO.class, "user-bo.yml");
        po = readYml(UserPO.class, "user-po.yml");
    }


    @Test
    public void toUserBO() {
        UserBO actual = converter.toUserBO(po);

        assertThat(actual, is(bo));
    }

    @Test
    public void toUserPO() {
        UserPO actual = converter.toUserPO(bo);

        assertThat(actual, is(po));
    }

    @Test
    public void toUserBOList() {
        List<UserBO> actualList = converter.toUserBOList(Collections.singletonList(po));

        assertThat(actualList, hasSize(1));
        assertThat(actualList.get(0), is(bo));
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