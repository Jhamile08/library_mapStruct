package com.riwi.library.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Column( nullable = false)
    private String user_name;
    @Column( nullable = false)
    private String password;
    @Email
    @Column( nullable = false)
    private String email;
    @Column( nullable = false)
    private String full_name;
    @Column( nullable = false)
    private String role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude // @121312312
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "user_id",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Reservation> reservation;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude // @121312312
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "user_id",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Loan> loan;
}
