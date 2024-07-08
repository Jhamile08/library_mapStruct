package com.riwi.library.infrastructure.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.library.api.dto.request.BookRequest;
import com.riwi.library.api.dto.response.BookResponse;
import com.riwi.library.domain.entities.Book;
import com.riwi.library.domain.repositories.BookRepository;
import com.riwi.library.domain.repositories.LoanRepository;
import com.riwi.library.domain.repositories.ReservationRepository;
import com.riwi.library.domain.repositories.UserRepository;
import com.riwi.library.infrastructure.abstract_service.IBookService;
import com.riwi.library.infrastructure.helpers.mapper.BookMapper;
import com.riwi.library.utils.Enums.SortType;
import com.riwi.library.utils.Enums.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService implements IBookService{
    
    @Autowired
    private final BookRepository BookRepository;
    @Autowired
    private final BookMapper bookMapper;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final LoanRepository loanRepository;
    
    @Override
    public BookResponse create(BookRequest request) {
        Book book = bookMapper.requestToEntity(request);
        book.setReservation(new ArrayList<>());
        book.setLoan(new ArrayList<>());
        return bookMapper.entityToResponse(this.BookRepository.save(book));
    }

    @Override
    public BookResponse get(Long id) {
        return bookMapper.entityToResponse(this.find(id));
    }

    @Override
    public BookResponse update(BookRequest request, Long id) {

        Book book = this.find(id);
        Book bookUpdate = bookMapper.requestToEntity(request);
        bookUpdate.setId(id);
        bookUpdate.setReservation(book.getReservation());
        bookUpdate.setLoan(book.getLoan());

        return bookMapper.entityToResponse(this.BookRepository.save(bookUpdate));

    }

    @Override
    public void delete(Long id) {
        
        Book book = this.find(id);
        this.BookRepository.delete(book);

    }

    @Override
    public Page<BookResponse> getAll(int page, int size, SortType sort) {
        
        if (page <0) page = 0;

        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.BookRepository.findAll(pagination)
                .map(bookMapper::entityToResponse);

    }
    
    private Book find(Long id) {
        return this.BookRepository.findById(id)
                    .orElseThrow(()-> new BadRequestException("No hay clientes con el id suministrado"));
    }
}
