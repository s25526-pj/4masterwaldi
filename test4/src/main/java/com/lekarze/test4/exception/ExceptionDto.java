package com.lekarze.test4.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    private String message;

    public static ExceptionDto fromException(Exception e) {
        return ExceptionDto.builder()
                .time(LocalDateTime.now())
                .message(e.getMessage())
                .build();
    }
}
