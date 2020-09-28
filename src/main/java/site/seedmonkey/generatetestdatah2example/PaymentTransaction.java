package site.seedmonkey.generatetestdatah2example;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class PaymentTransaction {

    String id;
    BigDecimal amount;
    PaymentMethod method;
    Timestamp dateOccurred;

    public enum PaymentMethod {
        VISA, MASTERCARD, AMEX, PAYPAL
    }
}
