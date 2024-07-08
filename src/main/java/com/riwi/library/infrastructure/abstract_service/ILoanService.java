package com.riwi.library.infrastructure.abstract_service;

import com.riwi.library.api.dto.request.LoanRequest;
import com.riwi.library.api.dto.response.LoanResponse;

public interface ILoanService extends CrudService<LoanRequest, LoanResponse, Long> {
    public final String FIELD_BY_SORT = "lean_date";
}
