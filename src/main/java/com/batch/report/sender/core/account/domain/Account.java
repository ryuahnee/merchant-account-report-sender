package com.batch.report.sender.core.account.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TB_MERCHANT_ACCOUNT")
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cpid", length = 10, nullable = false)
    @Comment("구분값")
    private String cpid;

    @Column(name = "business_number", length = 10, nullable = false)
    @Comment("사업자등록번호")
    private String businessNumber;

    @Column(name = "merchant_name", length = 100, nullable = false)
    @Comment("가맹점명")
    private String merchantName;

    @Column(name = "account_number", length = 30, nullable = false)
    @Comment("계좌번호")
    private String accountNumber;

    @Column(name = "bank_code", length = 3, nullable = false)
    @Comment("은행코드")
    private String bankCode;

    @Column(name = "bank_name", length = 50)
    @Comment("은행명")
    private String bankName;

    @Column(name = "account_holder", length = 100)
    @Comment("예금주")
    private String accountHolder;

    @Column(name = "status", length = 1, nullable = false)
    @Comment("상태 (Y: 사용, N: 미사용)")
    private String status;

    @Column(name = "created_by", length = 50, nullable = false)
    @Comment("등록자")
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    @Comment("등록일시")
    private LocalDateTime createdAt;

    @Column(name = "updated_by", length = 50)
    @Comment("수정자")
    private String updatedBy;

    @Column(name = "updated_at")
    @Comment("수정일시")
    private LocalDateTime updatedAt;

    @Builder
    public Account(Long id, String cpid, String businessNumber, String merchantName, String accountNumber, String bankCode, String bankName, String accountHolder, String status, String createdBy, LocalDateTime createdAt, String updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.cpid = cpid;
        this.businessNumber = businessNumber;
        this.merchantName = merchantName;
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.accountHolder = accountHolder;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
