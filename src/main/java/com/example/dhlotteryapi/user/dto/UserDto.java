package com.example.dhlotteryapi.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "아이디는 빈값을 허용하지 않습니다.")
    private String account; // 로그인 아이디
    @NotBlank(message = "비밀번호는 빈값을 허용하지 않습니다.")
    private String password; // 로그인 비밀번호
    // @todo 스케쥴러 private Boolean isSchedule; // 스케쥴러 사용여부
}
