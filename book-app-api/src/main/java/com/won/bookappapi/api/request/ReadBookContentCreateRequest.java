package com.won.bookappapi.api.request;

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
@ApiModel(value = "읽은 책 글귀 저장 Request Model")
public class ReadBookContentCreateRequest {
    @Positive
    @ApiModelProperty(value = "읽은 책 아이디", required = true)
    private Long readBookId;

    @NotBlank
    @ApiModelProperty(value = "좋은 글귀 리스트")
    private List<String> contents;
}
