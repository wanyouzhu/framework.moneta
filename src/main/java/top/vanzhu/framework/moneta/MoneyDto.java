package top.vanzhu.framework.moneta;

import java.math.BigDecimal;

public class MoneyDto {
    private BigDecimal number;
    private String currency;

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
