package site.seedmonkey.generatetestdatah2example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(PaymentTransactionService.class)
class PaymentTransactionServiceTest {

    @Autowired
    PaymentTransactionService paymentTransactionService;

    @Test
    void getTxByDate() {
        List<PaymentTransaction> allTransactionsFromDay = paymentTransactionService.getAllTransactionsFromDay(LocalDate.of(2020, 9, 15));
        assertThat(allTransactionsFromDay.size()).isEqualTo(1000);
        for(PaymentTransaction tx : allTransactionsFromDay) {
            System.out.println(tx.toString());
        }
    }

}