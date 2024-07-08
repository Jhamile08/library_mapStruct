package com.riwi.library.infrastructure.helpers.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.riwi.library.api.dto.request.BookRequest;
import com.riwi.library.api.dto.response.BookResponse;
import com.riwi.library.domain.entities.Book;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    
    BookResponse entityToResponse(Book book);

    @InheritInverseConfiguration
    Book responseToEntity(BookResponse bookResponse);


    Book requestToEntity(BookRequest bookRequest);

    List<BookResponse> bookListToResponseList(List<Book> book);

}
