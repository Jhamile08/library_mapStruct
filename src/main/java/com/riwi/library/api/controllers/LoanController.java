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

import com.riwi.library.api.dto.request.LoanRequest;
import com.riwi.library.api.dto.response.LoanResponse;

import com.riwi.library.infrastructure.abstract_service.ILoanService;
import com.riwi.library.utils.Enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/loan")
@AllArgsConstructor
public class LoanController {
        
    private final ILoanService loanService;

    @Operation(summary = "getAll book", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<Page<LoanResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.loanService.getAll(page - 1, size, sortType));
    }

    @Operation(summary = "get user", description = "Retrieve a list of all users")
    @GetMapping(path = "/{id}")
    public ResponseEntity<LoanResponse> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.loanService.get(id));
    }

    @Operation(summary = "insert user", description = "Retrieve a list of all users")
    @PostMapping
    public ResponseEntity<LoanResponse> insert(
            @Validated @RequestBody LoanRequest request) {
        return ResponseEntity.ok(this.loanService.create(request));
    }

    @Operation(summary = "update user", description = "Retrieve a list of all users")
    @PutMapping(path = "/{id}")
    public ResponseEntity<LoanResponse> update(
            @Validated @RequestBody LoanRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.loanService.update(request, id));
    }

    @Operation(summary = "delete user", description = "Retrieve a list of all users")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
