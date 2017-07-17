package top.vanzhu.framework.moneta;

import com.google.common.base.Objects;
import org.springframework.util.Assert;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.of(BigDecimal.ZERO, "CNY");
    private final BigDecimal number;
    private final CurrencyUnit currency;

    public Money(BigDecimal number, CurrencyUnit currency) {
        Assert.notNull(number, "The number of a Money can not be null.");
        Assert.notNull(currency, "The currency of a Money can not be null.");
        this.number = number;
        this.currency = currency;
    }

    public static Money of(BigDecimal number, String currencyCode) {
        return new Money(number, CurrencyUnit.forCode(currencyCode));
    }

    public static Money of(MoneyDto dto) {
        Assert.notNull(dto, "Can NOT create money from null dto");
        return of(dto.getNumber(), dto.getCurrency());
    }

    public Money add(Money y) {
        checkCurrency(y);
        return new Money(number.add(y.getNumber()), currency);
    }

    public Money subtract(Money y) {
        checkCurrency(y);
        return new Money(number.subtract(y.getNumber()), currency);
    }

    public static int compare(Money x, Money y) {
        x.checkCurrency(y);
        return x.getNumber().compareTo(y.getNumber());
    }

    private void checkCurrency(Money y) {
        if (!currency.equals(y.currency)) {
            throw new MonetaException("Currency mismatched: " + currency + "/" + y.currency);
        }
    }

    public BigDecimal getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equal(getNumberStripped(), money.getNumberStripped()) && Objects.equal(currency, money.currency);
    }

    public BigDecimal getNumberStripped() {
        return getNumber().stripTrailingZeros();
    }

    @Override
    public String toString() {
        return currency + " " + number;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    public MoneyDto toDto() {
        MoneyDto dto = new MoneyDto();
        dto.setNumber(number);
        dto.setCurrency(currency.getCode());
        return dto;
    }
}
