package com.riwi.library.api.dto.response;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationBasicResponse {

    private Long id;
    private LocalDate reservation_date;
    private String status;
    
}
