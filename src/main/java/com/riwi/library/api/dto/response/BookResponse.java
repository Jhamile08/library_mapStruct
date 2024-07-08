package com.riwi.library.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private Integer publication_year;
    private String genre;
    private String isbn;
    private List<ReservationBasicResponse> reservations;
    private List<LoanBasicResponse> loans;
    
}
