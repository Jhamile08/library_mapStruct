package com.riwi.library.infrastructure.abstract_service;

import com.riwi.library.api.dto.request.UserRequest;
import com.riwi.library.api.dto.response.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long> {
    public final String FIELD_BY_SORT = "dateTime";
}
