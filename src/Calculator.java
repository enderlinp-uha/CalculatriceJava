import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final String BLANK_VALUE = "";
    private final String EMPTY_VALUE = "Vide";
    private final String UNDEFINED_VALUE = "Non défini"; // Division par zéro

    private String input;
    private String history;

    public Calculator() {
        this.input = BLANK_VALUE;
        this.history = BLANK_VALUE;
    }

    private void clear() {
        this.input = BLANK_VALUE;
        this.history = EMPTY_VALUE;
    }

    private void compute() {
        if (this.input.contains("/0")) {
            this.input = UNDEFINED_VALUE;
        }

        String operator = "+";

        List<Double> numbers = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int index = 0; index < this.input.length(); index++) {
            String value = String.valueOf(this.input.charAt(index));
            List<String> operators = List.of("+", "-", "/", "*", "%");

            if (!operators.contains(value)) {
                number.append(value);
            }

            if (operators.contains(value) || index == this.input.length() - 1) {
                double d = Double.parseDouble(number.toString());
                switch (operator) {
                    case "+" -> numbers.add(d);
                    case "-" -> numbers.add(-d);
                    case "/" -> numbers.set(numbers.size() - 1, numbers.getLast() / d);
                    case "*" -> numbers.set(numbers.size() - 1, numbers.getLast() * d);
                    case "%" -> numbers.set(numbers.size() - 1, numbers.getLast() % d);
                }

                operator = value;
                number = new StringBuilder();
            }
        }

        double sum = 0;
        for (double d : numbers) {
            sum += d;
        }

        this.input = format(sum);

    }

    private String format(Double input) {
        String value;
        if (input % 1 == 0) {
            value = String.valueOf(input.intValue());
        } else {
            value = input.toString();
        }
        return value;
    }

    public String getHistory() {
        return this.history;
    }

    public String getResult() {
        return this.input;
    }

    public void insert(String character) {
        this.input += character;
        this.history = this.input;
    }

    public void update(EAction action) {
        switch (action) {
            case EAction.CLEAR -> this.clear();
            case EAction.EQUAL -> this.compute();
        }
    }

    @Override
    public String toString() {
        return "Historique: " + this.history + ", Résultat: " + this.input;
    }
}
