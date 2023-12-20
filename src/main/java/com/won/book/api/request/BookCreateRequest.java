package com.won.book.api.request;

import com.won.book.domain.book.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * BookCreateRequest
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "책 저장 Request Model")
public class BookCreateRequest {
    @NotBlank
    @ApiModelProperty(value = "책 이름", required = true)
    private String title;

    @NotBlank
    @ApiModelProperty(value = "저자", required = true)
    private String author;

    @Positive
    @ApiModelProperty(value = "가격")
    private int price;

    @ApiModelProperty(value = "출판사")
    private String publisher;

    @NotBlank
    @ApiModelProperty(value = "분류", required = true)
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
