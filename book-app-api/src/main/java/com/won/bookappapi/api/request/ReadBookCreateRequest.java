package com.won.bookappapi.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.domain.ReadBook;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ApiModel(value = "읽은 책 저장 Request Model")
public class ReadBookCreateRequest {
    @Positive
    @ApiModelProperty(value = "책 아이디", required = true)
    private Long bookId;

    @NotBlank
    @ApiModelProperty(value = "읽은 날", required = true)
    private String readAt;

    @Range(min = 0, max = 5)
    @ApiModelProperty(value = "평점", required = true)
    private int rating;

    @ApiModelProperty(value = "리뷰 리스트")
    private List<String> reviews;

    @ApiModelProperty(value = "좋은 글귀 리스트")
    private List<String> contents;

    public ReadBook toEntity() {
        LocalDate lastReadAt = LocalDateTimeUtil.toLocalDate(readAt);
        return ReadBook.create(1, rating, lastReadAt);
    }
}
