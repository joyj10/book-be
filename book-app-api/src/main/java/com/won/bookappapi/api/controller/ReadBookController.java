package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.ReadBookCreateRequest;
import com.won.bookappapi.api.request.ReadBookUpdateRequest;
import com.won.bookappapi.api.request.YearMonthRequest;
import com.won.bookappapi.service.ReadBookService;
import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookappapi.service.dto.ReadBookYearDto;
import com.won.bookcommon.exception.BusinessException;
import com.won.bookcommon.exception.ExceptionCode;
import com.won.bookcommon.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Read Book Controller", description = "읽은 책 API")
public class ReadBookController {

    private final ReadBookService readBookService;

    @GetMapping("/books/read")
    @Operation(summary = "읽은 책 리스트 조회", description = "읽은 책 리스트를 반환합니다.")
    public ResponseResult<List<ReadBookDto>> getReadBooks() {
        // 읽은 책 리스트 조회
        /** [추가작업] 페이징 처리 필요 */
        /** [추가작업] 검색 기능 필요 */
        /** [추가작업] 정렬 기능 필요 */
        return new ResponseResult<>(readBookService.getReadBooks(getUserId()));
    }

    @GetMapping("/books/read/{read-book-id}")
    @Operation(summary = "읽은 책 상세 조회", description = "읽은 책 상세 단건 조회합니다.")
    public ResponseResult<ReadBookDto> getReadBook(@PathVariable("read-book-id") Long readBookId) {
        return new ResponseResult<>(readBookService.getReadBook(getUserId(), readBookId));
    }

    @PostMapping("/books/read")
    @Operation(summary = "읽은 책 저장", description = "읽은 책을 저장합니다.")
    public ResponseResult<Long> save(@Valid @RequestBody ReadBookCreateRequest request) {
        Long readBookId = readBookService.save(getUserId(), request);
        return new ResponseResult<>(readBookId);
    }

    @PatchMapping("/books/read/{read-book-id}")
    @Operation(summary = "읽은 책 수정", description = "읽은 책을 수정합니다.")
    public ResponseResult<Long> update(@PathVariable("read-book-id") Long readBookId,
                                       @Valid @RequestBody ReadBookUpdateRequest request) {
        Long id = readBookService.update(getUserId(), readBookId, request);
        return new ResponseResult<>(id);
    }

    @DeleteMapping("/books/read/{read-book-id}")
    @Operation(summary = "읽은 책 삭제", description = "읽은 책을 삭제합니다.")
    public ResponseResult<Boolean> delete(@PathVariable("read-book-id") Long readBookId) {
        readBookService.delete(getUserId(), readBookId);
        return new ResponseResult<>(true);
    }

    @PostMapping("/books/read/{read-book-id}/repeat")
    @Operation(summary = "다시 읽은 책 저장", description = "다시 읽은 책을 저장합니다.")
    public String saveRepeat(@PathVariable("read-book-id") Long readBookId, @Valid @RequestBody ReadBookCreateRequest request) {
        return "Hello" + readBookId;
    }

    @GetMapping("/books/read/month")
    @ApiOperation(value = "특정 월의 읽은 책들 조회")
    @Operation(summary = "특정 월의 읽은 책들 조회", description = "특정 월의 읽은 책들을 조회합니다.")
    public ResponseResult<List<ReadBookDto>> getReadBookOfMonth(@Valid @RequestParam YearMonthRequest yearMonthRequest) {
        return new ResponseResult<>(readBookService.getReadBookOfMonth(getUserId(), yearMonthRequest));
    }

    @GetMapping("/books/read/year")
    @ApiOperation(value = "특정 년도의 읽은 책들 조회")
    @Operation(summary = "특정 년도의 읽은 책들 조회", description = "특정 년도의 읽은 책들을 조회합니다.")
    public ResponseResult<List<ReadBookYearDto>> getReadBookOfYear(int year) {
        validYear(year);
        return new ResponseResult<>(readBookService.getReadBookOfYear(getUserId(), year));
    }

    private void validYear(int year) {
        if (String.valueOf(year).length() != 4) {
            throw new BusinessException("년도는 4자리로 입력해야 합니다.", ExceptionCode.INVALID_PARAMETER);
        }
    }

    // 임시 처리 : JWT 작업 후 수정
    private Long getUserId() {
        return 1L;
    }
}
