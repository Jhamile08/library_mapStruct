package com.riwi.library.infrastructure.helpers.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import com.riwi.library.api.dto.request.UserRequest;
import com.riwi.library.api.dto.response.UserResponse;
import com.riwi.library.domain.entities.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    
    @Mappings({
        @Mapping(source = "user_name", target = "username"),
        @Mapping(source = "reservation", target = "reservations"),
        @Mapping(source = "loan", target = "loans")

    })
    UserResponse entityToResponse(User user);

    @InheritInverseConfiguration
    User responseToEntity(UserResponse userResponse);

    @Mappings({
        @Mapping(source = "username", target = "user_name"),
        @Mapping(target = "reservation", ignore = true),  
        @Mapping(target = "loan", ignore = true),
        @Mapping(target = "id", ignore = true)

    })
    User requestToEntity(UserRequest userRequest);

    List<UserResponse> UserListToResponseList(List<User> user);
}
