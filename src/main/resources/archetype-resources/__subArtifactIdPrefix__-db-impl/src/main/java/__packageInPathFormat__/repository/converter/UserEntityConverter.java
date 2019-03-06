package ${package}.repository.converter;

import org.mapstruct.Mapper;
import ${package}.repository.model.UserEntity;
import ${package}.repository.model.UserPO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityConverter {

    UserPO toUserPO(UserEntity entity);

    UserEntity toUserEntity(UserPO po);

    List<UserPO> toUserPOList(List<UserEntity> list);
}
