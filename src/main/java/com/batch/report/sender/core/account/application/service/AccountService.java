package com.batch.report.sender.core.account.application.service;

import com.batch.report.sender.core.account.domain.AccountHistory;
import com.batch.report.sender.core.account.util.DateUtil;
import com.batch.report.sender.data.jpa.AccountHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    private final AccountHistoryRepository accountHistoryRepository;

    public AccountService(AccountHistoryRepository accountHistoryRepository) {
        this.accountHistoryRepository = accountHistoryRepository;
    }

    //어제날짜로 변경된 히스토리 싹 가져옴
    public List<AccountHistory> findUpdatedAccountHistories(){

        log.info("start={}",DateUtil.getYesterdayStart());
        log.info("end={}",DateUtil.getYesterdayEnd());
        return  accountHistoryRepository.findUpdatedAccountHistories(
                DateUtil.getYesterdayStart(), DateUtil.getYesterdayEnd()
        );

    }

    // 어제날짜 기준으로 U인것 싹

    // 어제날짜 기준으로 I인것 싹





    //엊그제 그전 이력과 비교

    // 변경할 이력이 있으면,

    // 현재 데이터와




}
