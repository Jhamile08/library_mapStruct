package com.riwi.library.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @NotNull(message = "Lean date is required")
    private LocalDate lean_date;
    @FutureOrPresent(message = "Retuning have to be in the future")
    @NotNull(message = "Retuning date are required")
    private LocalDate return_date;
    @NotBlank(message = "Stutus is required")
    private String status;
    @NotBlank(message = "Required User id!")
    private Long user_id;
    @NotBlank(message = "Required Book id!")
    private Long book_id;

}
