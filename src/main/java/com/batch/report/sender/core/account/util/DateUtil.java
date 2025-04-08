package com.batch.report.sender.core.account.util;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
public class DateUtil {
    /**
     * 어제 날짜의 시작 시간 (00:00:00) 반환
     */
    public static LocalDateTime getYesterdayStart() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return yesterday.atStartOfDay(); // 00:00:00으로 설정
    }

    /**
     * 어제 날짜의 종료 시간 (23:59:59.999999999) 반환
     */
    public static LocalDateTime getYesterdayEnd() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return yesterday.atTime(LocalTime.MAX); // 23:59:59.999999999로 설정
    }


}
