package com.riwi.library.infrastructure.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.library.api.dto.request.UserRequest;
import com.riwi.library.api.dto.response.UserResponse;
import com.riwi.library.domain.entities.User;
import com.riwi.library.domain.repositories.UserRepository;
import com.riwi.library.infrastructure.abstract_service.IUserService;
import com.riwi.library.infrastructure.helpers.mapper.UserMapper;
import com.riwi.library.utils.Enums.SortType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;

    @Override
    public UserResponse create(UserRequest request) {
        User user = userMapper.requestToEntity(request);
        user.setReservation(new ArrayList<>());
        user.setLoan(new ArrayList<>());
        return userMapper.entityToResponse((this.userRepository.save(user)));
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        User user = this.find(id);

        User userUpdate = userMapper.requestToEntity(request);
        userUpdate.setId(id);
        userUpdate.setReservation(user.getReservation());
        userUpdate.setLoan(user.getLoan());
        
        return userMapper.entityToResponse(this.userRepository.save(userUpdate));
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {
        if (page <0) page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.userRepository.findAll(pagination)
                .map(userMapper::entityToResponse);
    }
    

    
    @Override
    public UserResponse get(Long id) {
        return userMapper.entityToResponse(this.find(id));
    }

    @Override
    public void delete(Long id) {
        User user = this.find(id);
        this.userRepository.delete(user);
    }

    private User find(Long id) {
        return this.userRepository.findById(id)
                    .orElseThrow(()-> new com.riwi.library.utils.Enums.exceptions.BadRequestException("No hay clientes con el id suministrado"));
    }




}
