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
//    @Schema(description = "책 이름", example = "헬로우 자바!", accessMode = READ_ONLY)
    private String title;

    @NotBlank
//    @Schema(description = "저자", example = "김자바", accessMode = READ_ONLY)
    private String author;

    @Positive
//    @Schema(description = "가격", example = "10000", accessMode = READ_ONLY)
    private Integer price;

//    @Schema(description = "출판사", accessMode = READ_ONLY)
    private String publisher;

    @NotBlank
//    @Schema(description = "분류", example = "IT", required = true)
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
