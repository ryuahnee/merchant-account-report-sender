package com.batch.report.sender.data;

import com.batch.report.sender.core.account.domain.AccountHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountHistoryRepositoryCustom {
    // 수정된 계좌 조회
    List<AccountHistory> findUpdatedAccountHistories( LocalDateTime start,LocalDateTime end);

    //신규로 등록된 계좌 조회
    List<AccountHistory>  findNewAccountHistories(LocalDateTime localDateTime);
}
