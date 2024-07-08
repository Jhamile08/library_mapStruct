package com.riwi.library.infrastructure.abstract_service;

import com.riwi.library.api.dto.request.ReservationRequest;
import com.riwi.library.api.dto.response.ReservationResponse;

public interface IReservationService extends CrudService<ReservationRequest, ReservationResponse, Long> {
    public final String FIELD_BY_SORT = "reservation_date";
}
