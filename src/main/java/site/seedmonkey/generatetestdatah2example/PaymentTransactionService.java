package site.seedmonkey.generatetestdatah2example;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentTransactionService {

    public static final String query = "SELECT * FROM PAYMENT_TRANSACTION WHERE DATE_OCCURRED >= :lowerBound AND DATE_OCCURRED <= :upperBound";

    public PaymentTransactionService(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RowMapper<PaymentTransaction> rowMapper = (resultSet, i) -> {
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setId(resultSet.getString("ID"));
        paymentTransaction.setAmount(resultSet.getBigDecimal("AMOUNT"));
        paymentTransaction.setMethod(PaymentTransaction.PaymentMethod.valueOf(resultSet.getString("METHOD")));
        paymentTransaction.setDateOccurred(resultSet.getTimestamp("DATE_OCCURRED"));
        return paymentTransaction;
    };

    public List<PaymentTransaction> getAllTransactionsFromDay(LocalDate date) {
        Timestamp lowerBound = Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT));
        Timestamp upperBound = Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MAX));
        Map<String,Object> params = new HashMap<>();
        params.put("lowerBound",lowerBound);
        params.put("upperBound",upperBound);
        return namedParameterJdbcTemplate.query(query, params,rowMapper);
    }
}
