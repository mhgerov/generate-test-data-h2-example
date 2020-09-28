package site.seedmonkey.generatetestdatah2example.test;

import site.seedmonkey.generatetestdatah2example.PaymentTransaction;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class H2StoredProcedures {

    private static String query = "INSERT INTO PAYMENT_TRANSACTION (ID,AMOUNT,METHOD,DATE_OCCURRED) VALUES (UUID(),?,?,?)";
    private static PaymentTransaction.PaymentMethod[] methods = PaymentTransaction.PaymentMethod.values();

    public static void generateTransactionsForDate(Connection conn, String date, int numTransactions) throws SQLException {
        LocalDate localDate = LocalDate.parse(date);

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        Random gen = new Random();

        BigDecimal amount;
        PaymentTransaction.PaymentMethod method;
        Timestamp dateOccurred;

        for (int i =0; i<numTransactions; i++) {
            amount = new BigDecimal((gen.nextDouble()*999)+1);
            method = methods[gen.nextInt(methods.length)];
            dateOccurred = Timestamp.valueOf(LocalDateTime.of(localDate, LocalTime.of(gen.nextInt(24),gen.nextInt(60),gen.nextInt(60),gen.nextInt((int)1E+9))));
            preparedStatement.setBigDecimal(1,amount);
            preparedStatement.setString(2, String.valueOf(method));
            preparedStatement.setTimestamp(3,dateOccurred);
            preparedStatement.executeUpdate();
            conn.commit();
        }
    }
}
