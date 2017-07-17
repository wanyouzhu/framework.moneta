package top.vanzhu.framework.moneta;

import com.google.common.base.Objects;

import java.util.Currency;

public class CurrencyUnit {
    private final String code;

    public CurrencyUnit(String code) {
        this.code = code;
    }

    public static CurrencyUnit forCode(String currencyCode) {
        return new CurrencyUnit(Currency.getInstance(currencyCode).getCurrencyCode());
    }

    public String getCode() {
        return code;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyUnit that = (CurrencyUnit) o;
        return Objects.equal(code, that.code);
    }

    @Override
    public String toString() {
        return code;
    }
}
