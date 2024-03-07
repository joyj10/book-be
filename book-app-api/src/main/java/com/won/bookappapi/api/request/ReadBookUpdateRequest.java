package com.won.bookappapi.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ApiModel(value = "읽은 책 수정 Request Model")
public class ReadBookUpdateRequest {
    @NotBlank
    @ApiModelProperty(value = "읽은 날", required = true)
    private String readAt;

    @Range(min = 0, max = 5)
    @ApiModelProperty(value = "평점", required = true)
    private Integer rating;
}
