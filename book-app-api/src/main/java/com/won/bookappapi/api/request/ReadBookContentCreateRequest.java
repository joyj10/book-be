package com.won.bookappapi.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ApiModel(value = "읽은 책 글귀 저장 Request Model")
public class ReadBookContentCreateRequest {
    @Positive
    @ApiModelProperty(value = "읽은 책 아이디", required = true)
    private Long readBookId;

    @NotBlank
    @ApiModelProperty(value = "좋은 글귀 리스트")
    private List<String> contents;
}
