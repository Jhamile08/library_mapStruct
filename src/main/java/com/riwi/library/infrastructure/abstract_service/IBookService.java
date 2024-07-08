package com.riwi.library.infrastructure.abstract_service;

import com.riwi.library.api.dto.request.BookRequest;
import com.riwi.library.api.dto.response.BookResponse;

public interface IBookService extends CrudService<BookRequest, BookResponse, Long> {
    public final String FIELD_BY_SORT = "title";
}
