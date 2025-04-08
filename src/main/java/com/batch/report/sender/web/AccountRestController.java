package com.batch.report.sender.web;

import com.batch.report.sender.bath.AccountChangeReportBatchConfig;
import com.batch.report.sender.core.account.application.service.AccountService;
import com.batch.report.sender.core.account.domain.AccountHistory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountRestController {

    private final AccountService accountService;
    private final JobLauncher jobLauncher;
    private final Job accountJob;

    public AccountRestController(AccountService accountService, JobLauncher jobLauncher, Job accountJob) {
        this.accountService = accountService;
        this.jobLauncher = jobLauncher;
        this.accountJob = accountJob;
    }

    @GetMapping
    public ResponseEntity<List<AccountHistory>> test(){
        return ResponseEntity.ok(accountService.findUpdatedAccountHistories());
    }
    @GetMapping("/report-job")
    public String runAccountChangeReportJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(accountJob, jobParameters);

            return "작업이 시작되었습니다.";
        } catch (Exception e) {
            return "오류 발생: " + e.getMessage();
        }
    }
}
