package Model.Persistence;

import Model.Currency;
import Model.exchangeRate;

public interface ExchangeRateMethods {
    exchangeRate load(Currency from, Currency to);
}
