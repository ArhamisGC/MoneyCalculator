package Model.Persistence;

import Model.Currency;
import Model.exchangeRate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ExchangeRateLoader implements ExchangeRateMethods{

    @Override
    public exchangeRate load(Currency from, Currency to) {
        try {
            return new exchangeRate(from, to, read(from.getName(), to.getName()));
        } catch (IOException ex) {
            return null;
        }
    }

    private double read(String from, String to) throws IOException {
        String url = data(from, to);
        return Double.parseDouble(url);
    }

    private String data(String from, String to) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + from.toLowerCase() + "/" + to.toLowerCase() + ".json").openStream()));
        String input;
        input = reader.readLine();
        input = reader.readLine();
        input = reader.readLine();
        reader.close();
        return input.substring(11);
    }
}
