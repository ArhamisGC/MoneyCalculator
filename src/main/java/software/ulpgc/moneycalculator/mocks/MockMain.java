package software.ulpgc.moneycalculator.mocks;

import software.ulpgc.moneycalculator.controller.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.interfaces.*;
import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

public class MockMain {
    public static void main(String[] args) {
        List<Currency> currencies = new MockCurrencyLoader().load();
        MoneyDialog moneyDialog = new MockMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = new MockCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = new MockMoneyDisplay();
        ExchangeRateLoader exchangeRateLoader = new MockExchangeRateLoader();
        Command command = new ExchangeMoneyCommand(moneyDialog, currencyDialog, exchangeRateLoader, moneyDisplay);

        for (Currency c: currencies) System.out.println(c);
        command.execute();
    }
}
