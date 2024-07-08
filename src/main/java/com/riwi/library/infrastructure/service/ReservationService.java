package com.riwi.library.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.library.api.dto.request.ReservationRequest;
import com.riwi.library.api.dto.response.ReservationResponse;
import com.riwi.library.domain.entities.Book;
import com.riwi.library.domain.entities.Reservation;
import com.riwi.library.domain.entities.User;
import com.riwi.library.domain.repositories.BookRepository;
import com.riwi.library.domain.repositories.ReservationRepository;
import com.riwi.library.domain.repositories.UserRepository;
import com.riwi.library.infrastructure.abstract_service.IReservationService;
import com.riwi.library.infrastructure.helpers.mapper.ReservationMapper;
import com.riwi.library.utils.Enums.SortType;
import com.riwi.library.utils.Enums.exceptions.BadRequestException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ReservationService implements IReservationService{
    
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ReservationMapper reservationMapper;


    @Override
    public ReservationResponse create(ReservationRequest request) {

        User user = this.userRepository.findById(request.getUserId())
        .orElseThrow(() -> new BadRequestException("No hay usuario con el id suministrado"));

        Book book = this.bookRepository.findById(request.getBookId())
        .orElseThrow(() -> new BadRequestException("No hay usuario con el id suministrado"));

        Reservation reservation = reservationMapper.requestToEntity(request);
        reservation.setUser(user);
        reservation.setBook(book);

        return reservationMapper.entityToResponse(this.reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse get(Long id) {
        return reservationMapper.entityToResponse(this.find(id));
    }

    @Override
    public ReservationResponse update(ReservationRequest request, Long id) {
        Reservation reservation = this.find(id);

        User user = this.userRepository.findById(request.getUserId())
            .orElseThrow(() -> new BadRequestException("No hay usuarios con el id suministrado"));

        Book book = this.bookRepository.findById(request.getBookId())
            .orElseThrow(() -> new BadRequestException("No hay libros con el id suministrado"));
            
        reservation = reservationMapper.requestToEntity(request);
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setId(id);   
        
        return reservationMapper.entityToResponse(this.reservationRepository.save(reservation));
        
    }

    @Override
    public void delete(Long id) {
        this.reservationRepository.delete(this.find(id));
    }

    @Override
    public Page<ReservationResponse> getAll(int page, int size, SortType sort) {
            if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.reservationRepository.findAll(pagination)
                .map(reservationMapper::entityToResponse);

    }

    private Reservation find(Long id) {
        return this.reservationRepository.findById(id)
        .orElseThrow(()-> new BadRequestException("No hay reservas con el id suministrado"));
    }
    

}
