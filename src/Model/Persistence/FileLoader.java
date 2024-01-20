package Model.Persistence;

import Model.Currency;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader implements PersistenceMethods{

    private final String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Currency[] getCurrencies(){
        ArrayList<Currency> arr = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (true){
                String line = reader.readLine();
                if (line == null) break;
                arr.add(createCurrency(line));
            }
        }catch(IOException e){
        }
        Currency[] res = new Currency[arr.size()];
        res = arr.toArray(res);
        return res;
    }

    private Currency createCurrency(String line) {
        String[] object = line.split(",");
        return new Currency(object[0], object[1], object[2]);
    }

}
