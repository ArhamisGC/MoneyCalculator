package MoneyCalculator;

import Controller.Controller;
import Model.Currency;
import Model.Persistence.FileLoader;
import View.View;

public class main {
    public static void main(String[] args){
        FileLoader file = new FileLoader("currencies.txt");
        String[] arr = toArrayString(file);
        View vista = new View(arr, file.getCurrencies());
        Controller controller = new Controller(vista, file.getCurrencies());
    }

    private static String[] toArrayString(FileLoader file) {
        Currency[] arr = file.getCurrencies();
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i].getName();
        }
        return res;
    }
}
