package com.won.bookappapi.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "읽은 책 수정 Request Model")
public class ReadBookUpdateRequest {
    @NotBlank
    @ApiModelProperty(value = "읽은 날", required = true)
    private String readAt;

    @Range(min = 0, max = 5)
    @ApiModelProperty(value = "평점", required = true)
    private int rating;
}
