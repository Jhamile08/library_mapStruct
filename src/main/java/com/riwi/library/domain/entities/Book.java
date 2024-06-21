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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column( nullable = false)
    private String author;
    @Column( nullable = false)
    private Integer publication_year;
    @Column( nullable = false, length = 50)
    private String genre;
    @Column( nullable = false, length = 20)
    private String isbn;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude // @121312312
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "book_id",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Reservation> reservation;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude // @121312312
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "book_id",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Loan> loan;


}
