package com.won.bookappapi.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "읽은 책 수정 Request Model")
public class ReadBookUpdateRequest {
    @NotBlank
    @ApiModelProperty(value = "읽은 날", required = true)
    private String readAt;

    @Positive
    @ApiModelProperty(value = "평점", required = true)
    private int rating;
}
