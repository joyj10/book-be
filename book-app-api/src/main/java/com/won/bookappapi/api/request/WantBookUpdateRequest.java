package com.won.bookappapi.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ApiModel(value = "읽고 싶은 책 수정 Request Model")
public class WantBookUpdateRequest {
    @NotBlank
    @ApiModelProperty(value = "추가일", required = true)
    private LocalDate addAt;

    @ApiModelProperty(value = "읽고 싶은 이유")
    private List<String> reasons;
}
