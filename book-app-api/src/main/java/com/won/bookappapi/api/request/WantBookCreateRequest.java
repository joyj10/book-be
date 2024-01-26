package com.won.bookappapi.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "읽고 싶은 책 저장 Request Model")
public class WantBookCreateRequest {
    @Positive
    @ApiModelProperty(value = "책 아이디", required = true)
    private Long bookId;

    @ApiModelProperty(value = "읽고 싶은 이유")
    private String reason;
}
