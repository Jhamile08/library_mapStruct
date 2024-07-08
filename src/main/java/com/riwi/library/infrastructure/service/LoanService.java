package com.riwi.library.infrastructure.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.library.api.dto.request.LoanRequest;
import com.riwi.library.api.dto.response.LoanResponse;
import com.riwi.library.domain.entities.Book;
import com.riwi.library.domain.entities.Loan;
import com.riwi.library.domain.entities.Reservation;
import com.riwi.library.domain.entities.User;
import com.riwi.library.domain.repositories.BookRepository;
import com.riwi.library.domain.repositories.LoanRepository;
import com.riwi.library.domain.repositories.UserRepository;
import com.riwi.library.infrastructure.abstract_service.ILoanService;
import com.riwi.library.infrastructure.helpers.mapper.BookMapper;
import com.riwi.library.infrastructure.helpers.mapper.LoanMapper;
import com.riwi.library.utils.Enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {
    
    @Autowired
    private final LoanRepository loanRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final LoanMapper loanMapper;
    
    @Override
    public LoanResponse create(LoanRequest request) {
        User user = this.userRepository.findById(request.getUser_id())
            .orElseThrow(() -> new com.riwi.library.utils.Enums.exceptions.BadRequestException("No hay usuarios con el id suministrado"));
        Book book = this.bookRepository.findById(request.getBook_id())
            .orElseThrow(() -> new com.riwi.library.utils.Enums.exceptions.BadRequestException("No hay libros con el id suministrado"));

        Loan loan = loanMapper.requestToEntity(request);
        loan.setBook(book);
        loan.setUser(user);
        return loanMapper.entityToResponse(this.loanRepository.save(loan));
    }

    @Override
    public LoanResponse get(Long id) {
        return loanMapper.entityToResponse(this.find(id));
    }

    @Override
    public LoanResponse update(LoanRequest request, Long id) {
        Loan loan = this.find(id);
        User user = this.userRepository.findById(request.getUser_id())
            .orElseThrow(() -> new com.riwi.library.utils.Enums.exceptions.BadRequestException("No hay usuarios con el id suministrado"));
        Book book = this.bookRepository.findById(request.getBook_id())
            .orElseThrow(() -> new com.riwi.library.utils.Enums.exceptions.BadRequestException("No hay libros con el id suministrado"));

        loan = loanMapper.requestToEntity(request);
        loan.setBook(book);
        loan.setUser(user);

        return loanMapper.entityToResponse(this.loanRepository.save(loan));
    }

    @Override
    public void delete(Long id) {
        this.loanRepository.delete(this.find(id));
    }

    @Override
    public Page<LoanResponse> getAll(int page, int size, SortType sort) {
                    if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        
        return this.loanRepository.findAll(pagination)
                .map(loanMapper::entityToResponse);
    }

    private Loan find(Long id) {
        return this.loanRepository.findById(id)
        .orElseThrow(()-> new com.riwi.library.utils.Enums.exceptions.BadRequestException("No hay reservas con el id suministrado"));
    }
    
}
