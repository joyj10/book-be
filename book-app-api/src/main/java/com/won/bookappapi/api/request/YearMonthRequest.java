package com.won.bookappapi.api.request;

import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "년월 Request")
public class YearMonthRequest {
    @Positive
    private int year;

    @Range(min = 1, max = 12, message = "1 ~ 12 숫자만 가능")
    private int month;
}
