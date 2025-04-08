package com.batch.report.sender.data.jpa;

import com.batch.report.sender.core.account.domain.Account;
import com.batch.report.sender.data.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
}
