package ${package}.service.converter;

import org.mapstruct.Mapper;
import ${package}.repository.model.UserPO;
import ${package}.service.model.UserBO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBOConverter {
    UserBO toUserBO(UserPO po);

    UserPO toUserPO(UserBO bo);

    List<UserBO> toUserBOList(List<UserPO> list);
}
