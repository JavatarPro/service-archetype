package ${package}.repository.converter;

import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import pro.javatar.commons.reader.YamlReader;
import ${package}.repository.domain.UserEntity;
import ${package}.repository.domain.UserPO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserEntityConverterTest {

    private UserEntityConverter converter;
    private UserEntity entity;
    private UserPO po;

    @Before
    public void setUp() throws Exception {
        converter = Mappers.getMapper(UserEntityConverter.class);
        entity = readYml(UserEntity.class, "user-entity.yml");
        po = readYml(UserPO.class, "user-po.yml");
    }

    @Test
    public void toUserPO() {
        UserPO actual = converter.toUserPO(entity);

        assertThat(actual, is(po));
    }

    @Test
    public void toUserEntity() {
        UserEntity actual = converter.toUserEntity(po);

        assertThat(actual, is(entity));
    }

    @Test
    public void toUserPOList() {
        List<UserPO> actualList = converter.toUserPOList(Collections.singletonList(entity));

        assertThat(actualList, hasSize(1));
        assertThat(actualList.get(0), is(po));
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