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
@ApiModel(value = "읽고 싶은 책 저장 Request Model")
public class WantBookCreateRequest {
    @Positive
    @ApiModelProperty(value = "책 아이디", required = true)
    private Long bookId;

    @NotBlank
    @ApiModelProperty(value = "추가일", required = true)
    private String addAt;

    @ApiModelProperty(value = "읽고 싶은 이유")
    private List<String> reasons;
}
