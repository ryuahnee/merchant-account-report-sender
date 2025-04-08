package com.batch.report.sender.data.jpa;

import com.batch.report.sender.core.account.domain.AccountHistory;
import com.batch.report.sender.data.AccountHistoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long>, AccountHistoryRepositoryCustom {
}
