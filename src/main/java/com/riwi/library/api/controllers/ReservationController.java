package com.riwi.library.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.library.api.dto.request.ReservationRequest;
import com.riwi.library.api.dto.response.ReservationResponse;
import com.riwi.library.infrastructure.abstract_service.IReservationService;
import com.riwi.library.utils.Enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/reservation")
@AllArgsConstructor
public class ReservationController {
    
    private final IReservationService reservationService;

    @Operation(summary = "getAll book", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<Page<ReservationResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.reservationService.getAll(page - 1, size, sortType));
    }

    @Operation(summary = "get user", description = "Retrieve a list of all users")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.get(id));
    }

    @Operation(summary = "insert user", description = "Retrieve a list of all users")
    @PostMapping
    public ResponseEntity<ReservationResponse> insert(
            @Validated @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(this.reservationService.create(request));
    }

    @Operation(summary = "update user", description = "Retrieve a list of all users")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> update(
            @Validated @RequestBody ReservationRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.reservationService.update(request, id));
    }

    @Operation(summary = "delete user", description = "Retrieve a list of all users")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
