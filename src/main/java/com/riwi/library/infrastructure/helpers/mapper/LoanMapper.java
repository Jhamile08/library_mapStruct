package com.riwi.library.infrastructure.helpers.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.riwi.library.api.dto.request.LoanRequest;
import com.riwi.library.api.dto.response.LoanResponse;
import com.riwi.library.domain.entities.Loan;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {

    LoanResponse entityToResponse(Loan loan);

    @InheritInverseConfiguration
    Loan responseToEntity(LoanResponse loanResponse);

    Loan requestToEntity(LoanRequest loanRequest);

    List<LoanResponse> loanListToResponseList(List<Loan> loan);
}
