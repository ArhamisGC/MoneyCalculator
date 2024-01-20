package View;

import Model.Currency;
import Model.Persistence.ExchangeRateLoader;
import Model.exchangeRate;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame{
    private JPanel Panel;
    private JLabel label;
    private JButton boton;
    private JTextField text;
    private JTextField text0;

    public JTextField getText() {
        return text;
    }

    public JTextField getText0() {
        return text0;
    }

    public JComboBox<String> getLista() {
        return lista;
    }

    public JComboBox<String> getLista0() {
        return lista0;
    }

    public JButton getBoton() {
        return boton;
    }
    private JComboBox<String> lista;
    private JComboBox<String> lista0;

    public View(String[] listas, Currency[] lista){
        initialize();
        initializeComponents(listas, lista);
        pack();
    }

    private void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(650,400));
        setTitle("MoneyCalculator");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents(String[] listas, Currency[] lista) {
        initializePanel();
        initializeButtons(lista);
        initializeTextsFields();
        updatePanel();
        initializeComboBoxes(listas);
        updatePanel();
        initializeLabel();
        updatePanel();
    }

    private void initializePanel() {
        Panel = new JPanel();
        Panel.setLayout(null);
        this.getContentPane().add(Panel);

    }

    private void initializeButtons(Currency[] lista) {
        boton = new JButton();
        boton.setText("Convert");
        boton.setBounds(250,150,100,25);
        boton.setEnabled(true);
        addListener(lista);
        Panel.add(boton);
    }

    private void initializeTextsFields() {
        text = new JTextField();
        text0 = new JTextField();

        text.setBounds(10,150,125,25);
        text.setText("Introduce amount");
        text.setEditable(true);

        text0.setBounds(375,150,125,25);
        text0.setEditable(false);

        Panel.add(text);
        Panel.add(text0);
    }

    private void initializeComboBoxes(String[] listas) {
        lista = new JComboBox<>(listas);
        lista0 = new JComboBox<>(listas);

        lista.setBounds(150,150,75,25);
        lista0.setBounds(520,150,75,25);

        Panel.add(lista);
        Panel.add(lista0);
    }

    private void updatePanel() {
        Panel.revalidate();
        Panel.repaint();
    }

    private void addListener(Currency[] lista2) {
        ActionListener Listener = new ActionListener() {
            private Currency from;
            private Currency to;
            private exchangeRate rate;

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double amount = Double.parseDouble(text.getText());
                    getCurrency();
                    getLoader();
                    text0.setText(String.valueOf(amount* rate.getAmount()));
                }catch(NumberFormatException exa){
                }

            }

            private void getCurrency() {
                from = lista2[lista.getSelectedIndex()];
                to = lista2[lista0.getSelectedIndex()];
            }

            private void getLoader() {
                ExchangeRateLoader loader = new ExchangeRateLoader();
                rate = loader.load(from, to);
            }
        };
        boton.addActionListener(Listener);
    }

    private void initializeLabel() {
        this.label = new JLabel("Money Calculator");
        label.setBounds(230,50,200,25);
        label.setFont(new Font("Calibri", Font.PLAIN, 20));
        Panel.add(label);
    }
}
