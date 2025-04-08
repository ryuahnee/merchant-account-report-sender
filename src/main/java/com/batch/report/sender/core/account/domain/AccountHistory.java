package com.batch.report.sender.core.account.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "TB_MERCHANT_ACCOUNT_HISTORY")
@NoArgsConstructor
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cpid", length = 10, nullable = false)
    @Comment("구분값")
    private String cpid;

    @Column(name = "new_account_number", length = 30, nullable = false)
    @Comment("변경 계좌번호")
    private String newAccountNumber;

    @Column(name = "new_bank_code", length = 3, nullable = false)
    @Comment("변경 은행코드")
    private String newBankCode;

    @Column(name = "new_bank_name", length = 50)
    @Comment("변경 은행명")
    private String newBankName;

    @Column(name = "merchant_name", length = 100)
    @Comment("변경 예금주")
    private String newAccountHolder;

    @Column(name = "changed_by", length = 50, nullable = false)
    @Comment("변경자")
    private String changedBy;

    @Column(name = "changed_at", nullable = false)
    @Comment("변경일시")
    private LocalDateTime changedAt;

    @Column(name = "action", length = 200)
    @Comment("로그")
    private String action;

    @Builder
    public AccountHistory(Long id, String cpid, String newAccountNumber, String newBankCode, String newBankName, String newAccountHolder, String changedBy, LocalDateTime changedAt, String action) {
        this.id = id;
        this.cpid = cpid;
        this.newAccountNumber = newAccountNumber;
        this.newBankCode = newBankCode;
        this.newBankName = newBankName;
        this.newAccountHolder = newAccountHolder;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
        this.action = action;
    }
}
