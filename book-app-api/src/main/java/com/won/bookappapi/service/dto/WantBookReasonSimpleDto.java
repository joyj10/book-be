package com.won.bookappapi.service.dto;

import com.won.bookdomain.domain.WantBookReason;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WantBookReasonSimpleDto {
    private Long id;
    private String reason;

    public static WantBookReasonSimpleDto from(WantBookReason wantBookReason) {
        return WantBookReasonSimpleDto.builder()
                .id(wantBookReason.getId())
                .reason(wantBookReason.getReason())
                .build();
    }
}
