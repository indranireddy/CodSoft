import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private JFrame frame;
    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel resultLabel;

    private Map<String, Double> exchangeRates;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter().createAndShowGUI());
    }

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        // Example exchange rates; in a real application, these would be fetched from an API
        exchangeRates.put("USD/RUB", 64.52);
        exchangeRates.put("RUB/USD", 0.0155);
        exchangeRates.put("USD/EUR", 0.85);
        exchangeRates.put("EUR/USD", 1.18);
        exchangeRates.put("USD/GBP", 0.74);
        exchangeRates.put("GBP/USD", 1.35);
        exchangeRates.put("USD/INR", 73.5);
        exchangeRates.put("INR/USD", 0.0136);
        exchangeRates.put("USD/JPY", 110.0);
        exchangeRates.put("JPY/USD", 0.0091);
        exchangeRates.put("USD/CAD", 1.25);
        exchangeRates.put("CAD/USD", 0.80);
        exchangeRates.put("USD/AUD", 1.35);
        exchangeRates.put("AUD/USD", 0.74);
        // Add more exchange rates as needed
    }

    private void createAndShowGUI() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        amountField = new JTextField(10);
        String[] currencies = {"USD / Dollar", "RUB / Ruble", "EUR / Euro", "GBP / Pound", "INR / Rupee", "JPY / Yen", "CAD / Canadian Dollar", "AUD / Australian Dollar"};
        fromCurrency = new JComboBox<>(currencies);
        toCurrency = new JComboBox<>(currencies);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        frame.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("From:"), gbc);

        gbc.gridx = 1;
        frame.add(fromCurrency, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(new JLabel("To:"), gbc);

        gbc.gridx = 1;
        frame.add(toCurrency, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(convertButton, gbc);

        gbc.gridy = 4;
        frame.add(resultLabel, gbc);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        frame.setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            if (from == null || to == null) {
                resultLabel.setText("Please select currencies.");
                return;
            }

            String fromCode = from.split(" / ")[0];
            String toCode = to.split(" / ")[0];
            String conversionKey = fromCode + "/" + toCode;

            if (exchangeRates.containsKey(conversionKey)) {
                double rate = exchangeRates.get(conversionKey);
                double convertedAmount = amount * rate;
                resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCode, convertedAmount, toCode));
            } else {
                resultLabel.setText("Conversion rate not available.");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount.");
        }
    }
}
