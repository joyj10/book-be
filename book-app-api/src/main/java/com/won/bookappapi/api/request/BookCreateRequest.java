package com.won.bookappapi.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.won.bookdomain.domain.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "책 저장 Request Model")
public class BookCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @Positive
    private Integer price;

    private String publisher;

    @NotBlank
    private String sort;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .price(price)
                .publisher(publisher)
                .isbn("NONE")
                .sort(sort)
                .build();
    }
}
