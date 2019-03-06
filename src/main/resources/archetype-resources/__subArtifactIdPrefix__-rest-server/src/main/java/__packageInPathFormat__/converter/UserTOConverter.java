package ${package}.converter;

import org.mapstruct.Mapper;
import ${package}.model.UserTO;
import ${package}.service.model.UserBO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTOConverter {

    UserTO toUserTO(UserBO bo);

    List<UserTO> toUserTOList(List<UserBO> list);

    UserBO toUserBO(UserTO user);
}
