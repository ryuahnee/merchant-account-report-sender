package com.batch.report.sender.data;

import com.batch.report.sender.core.account.domain.AccountHistory;
import com.batch.report.sender.core.account.domain.QAccount;
import com.batch.report.sender.core.account.domain.QAccountHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AccountHistoryRepositoryCustomImpl implements AccountHistoryRepositoryCustom {

        private final JPAQueryFactory queryFactory;

    // 어제날짜로 인입된 정보들 가져오기
    @Override
    public List<AccountHistory> findUpdatedAccountHistories(LocalDateTime start,LocalDateTime end) {
        QAccountHistory history = QAccountHistory.accountHistory;
        QAccount account = QAccount.account;

       return queryFactory
                .select(history)
                .from(history)
                .where(history.changedAt.between(start,end)).fetch();

    }

    @Override
    public List<AccountHistory> findNewAccountHistories(LocalDateTime localDateTime) {
        return null;
    }
}
