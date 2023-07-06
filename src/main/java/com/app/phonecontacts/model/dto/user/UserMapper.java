package com.app.phonecontacts.model.dto.user;

import com.app.phonecontacts.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestToUser(UserRequest request);
}
