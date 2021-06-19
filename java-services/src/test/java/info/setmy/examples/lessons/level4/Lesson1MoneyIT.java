package info.setmy.examples.lessons.level4;

import java.math.BigDecimal;
import java.util.Locale;
import javax.money.CurrencyQuery;
import javax.money.CurrencyQueryBuilder;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryException;
import javax.money.NumberValue;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRate;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Lesson1MoneyIT {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void money() {
        CurrencyUnit copUnit = Monetary.getCurrency("COP");
        CurrencyUnit eurUnit = Monetary.getCurrency("EUR");
        CurrencyUnit usdUnit = Monetary.getCurrency(Locale.US);

        MonetaryAmount usdAmount = Monetary.getDefaultAmountFactory()
                .setCurrency(usdUnit)
                .setNumber(55.12)
                .create();

        MonetaryAmount eurAmount = Monetary.getDefaultAmountFactory()
                .setCurrency(eurUnit)
                .setNumber(500_00)
                .create();

        MonetaryAmount euroMonetaryAmount1 = Money.of(123.45, eurUnit);
        MonetaryAmount euroMonetaryAmount2 = Money.of(new BigDecimal("321.54"), eurUnit);
        MonetaryAmount euroSum = euroMonetaryAmount1.add(euroMonetaryAmount2);

        MonetaryAmountFormat customFormat = MonetaryFormats.getAmountFormat(
                AmountFormatQueryBuilder.of(Locale.GERMAN)
                        .set(CurrencyStyle.NAME)
                        .set("pattern", "00,00,00,00.00 ¤")
                        .build());

        log.info("Sum: {}, with format: {}", euroSum, customFormat.format(euroSum));

        try {
            usdAmount.add(eurAmount);
        } catch (MonetaryException ex) {
            log.info("Adding error: ", ex);
        }

        ExchangeRateProvider exchangeRateProvider = MonetaryConversions.getExchangeRateProvider();
        ExchangeRateProvider ecbExchangeRateProvider = MonetaryConversions.getExchangeRateProvider("ECB");
        ExchangeRate rate = exchangeRateProvider.getExchangeRate("EUR", "USD");
        NumberValue factor = rate.getFactor();
        CurrencyUnit baseCurrency = rate.getBaseCurrency(); // EUR
        CurrencyUnit targetCurrency = rate.getCurrency(); // USD

        CurrencyConversion dollarConversion = MonetaryConversions.getConversion("USD");
        CurrencyConversion ecbDollarConversion = ecbExchangeRateProvider.getCurrencyConversion("USD");

        MonetaryAmount tenEuro = Money.of(10, "EUR");
        MonetaryAmount inDollar = tenEuro.with(ecbDollarConversion);
        log.info("Factor: {}", factor);
        log.info("Base curency: {}", baseCurrency);
        log.info("Target curency: {}", targetCurrency);
        log.info("In dollars: {}", inDollar);

        MonetaryAmountFormat germanFormat = MonetaryFormats.getAmountFormat(Locale.GERMAN);
        MonetaryAmountFormat canadaFormat = MonetaryFormats.getAmountFormat(Locale.CANADA);

        MonetaryAmount amount = Money.of(12345.67, "USD");

        log.info("Canada formatted: {}", canadaFormat.format(amount));
        log.info("German formatted: {}", germanFormat.format(amount));

        MonetaryAmount parsed = germanFormat.parse("12,4 USD");
        log.info("Parsed in dollars: {}", parsed);
    }
}
