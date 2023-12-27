package com.won.bookappapi.api.request;

import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.domain.ReadBook;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "읽은 책 저장 Request Model")
public class ReadBookCreateRequest {
    @Positive
    @ApiModelProperty(value = "책 아이디", required = true)
    private Long bookId;

    @NotBlank
    @ApiModelProperty(value = "읽은 날", required = true)
    private String readAt;

    @Positive
    @ApiModelProperty(value = "평점", required = true)
    private int rating;

    @ApiModelProperty(value = "리뷰 리스트")
    private List<String> reviews;

    @ApiModelProperty(value = "좋은 글귀 리스트")
    private List<String> contents;

    public ReadBook toEntity() {
        return ReadBook.builder()
                .readCount(1)
                .totalRating(rating)
                .lastReadAt(LocalDateTimeUtil.toLocalDate(readAt))
                .isDeleted(false)
                .build();
    }
}
