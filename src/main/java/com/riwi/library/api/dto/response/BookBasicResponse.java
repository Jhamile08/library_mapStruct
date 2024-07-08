package com.riwi.library.api.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookBasicResponse {
    private Long id;
    private String title;
    private String author;
    private Integer publication_year;
    private String genre;
    private String isbn;
}
