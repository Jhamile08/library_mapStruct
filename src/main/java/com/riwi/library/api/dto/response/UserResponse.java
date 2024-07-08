package com.riwi.library.api.dto.response;

import com.riwi.library.domain.entities.Loan;
import com.riwi.library.domain.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private String role;
    private List<Reservation> reservations;
    private List<Loan> loans;
    
}
