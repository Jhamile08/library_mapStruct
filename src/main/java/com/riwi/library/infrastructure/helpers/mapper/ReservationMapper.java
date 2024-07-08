package com.riwi.library.infrastructure.helpers.mapper;


import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.riwi.library.api.dto.request.ReservationRequest;
import com.riwi.library.api.dto.response.ReservationResponse;
import com.riwi.library.domain.entities.Reservation;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {

    ReservationResponse entityToResponse(Reservation reservation);

    @InheritInverseConfiguration
    Reservation responseToEntity(ReservationResponse reservationResponse);


    Reservation requestToEntity(ReservationRequest reservationRequest);

    List<ReservationResponse> reservationListToResponseList(List<Reservation> reservation);
}
