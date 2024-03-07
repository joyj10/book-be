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
