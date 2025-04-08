package com.batch.report.sender.bath;

import com.batch.report.sender.client.mail.MailSendService;
import com.batch.report.sender.core.account.application.service.AccountService;
import com.batch.report.sender.core.account.domain.AccountHistory;
import com.batch.report.sender.data.jpa.AccountHistoryRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.StepRegistry;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AccountChangeReportBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final AccountService accountService;
    private final MailSendService mailSendService;

    @Bean
    public Job accountJob() throws MessagingException {
      return new JobBuilder("accountJob",jobRepository)
              .listener(jobExecutionListener())
              .start(accountStep())
              .build();
    }

    @Bean
    public Step accountStep() throws MessagingException {
      return  new StepBuilder("accountStep", jobRepository)
                .<List<AccountHistory>, List<AccountHistory>>chunk(1, transactionManager)   // reader,processor의 리턴값
                .reader(accountChangeReader())              // 읽기
                .processor(accountChangeProcessor())        //데이터 처리
                .writer(accountChangeWriter())              // 쓰기
                .build();
    }

    @Bean
    public ItemReader<List<AccountHistory>> accountChangeReader(){
        List<AccountHistory> histories = accountService.findUpdatedAccountHistories();
        return new ListItemReader<>(List.of(histories));
    }

    //ItemProcessor<INPUT, OUTPUT>
    @Bean
    public ItemProcessor<List<AccountHistory>,List<AccountHistory>> accountChangeProcessor() {

        return histories -> {
            for (int i = 0; i < histories.size(); i++) {
                AccountHistory history = histories.get(i);
            }
            return histories;
        };
    }

    //ItemWriter<OUTPUT>
    @Bean
    public ItemWriter<List<AccountHistory>> accountChangeWriter() {
        return items -> {
            for (List<AccountHistory> historyList : items) {
                StringBuilder tableHtml = new StringBuilder();
                tableHtml.append("<h1>안녕하세요!</h1>");
                tableHtml.append("<p>이것은 <b>HTML</b> 형식의 테스트 이메일입니다.</p>");

                // 테이블 시작
                tableHtml.append("<table border='1' style='border-collapse: collapse;'>");
                tableHtml.append("<tr><th>가맹점ID</th><th>사업자번호</th><th>가맹점명</th><th>변경유형</th></tr>");

                // 각 계좌 변경 내역을 테이블 행으로 추가
                for (AccountHistory history : historyList) {
                    tableHtml.append("<tr>");
                    tableHtml.append("<td>").append(history.getCpid()).append("</td>");
                    tableHtml.append("<td>").append(history.getChangedAt()).append("</td>");
                    tableHtml.append("<td>").append(history.getNewAccountHolder()).append("</td>");
                    tableHtml.append("<td>").append(history.getNewBankName()).append("</td>");
                    tableHtml.append("</tr>");
                }

                // 테이블 종료
                tableHtml.append("</table>");

                // 총 건수 정보 추가
                tableHtml.append("<p>총 <b>").append(historyList.size()).append("</b>건의 계좌 변경 내역이 있습니다.</p>");

                String to = "ryuahneee@gmail.com";
                String subject = "[가맹점 계좌 변경]";

                try {
                    mailSendService.sendHtmlMessage(to, subject, tableHtml.toString());
                } catch (Exception e) {
                    throw new RuntimeException("이메일 발송 중 오류 발생", e);
                }
            }
        };
    }


    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("Job {} 시작됨", jobExecution.getJobInstance().getJobName());
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info("Job {} 완료됨, 상태: {}",
                        jobExecution.getJobInstance().getJobName(),
                        jobExecution.getStatus());
            }
        };
    }

}
