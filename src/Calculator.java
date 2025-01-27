import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final String BLANK_VALUE = "";
    private final String EMPTY_VALUE = "Vide";
    private final String UNDEFINED_VALUE = "Non défini";

    private String input;
    private String history;

    public Calculator() {
        this.input = BLANK_VALUE;
        this.history = BLANK_VALUE;
    }

    private Calculator clear() {
        this.input = BLANK_VALUE;
        this.history = EMPTY_VALUE;
        return this;
    }

    private Calculator compute() {
        if (this.input.contains("/0")) {
            this.input = UNDEFINED_VALUE;
            return this;
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
        for (double d : numbers) sum += d;
        this.input = format(sum);

        return this;
    }

    private String format(Double value) {
        double result;
        if (value % 1 == 0.0) {
            result = value.intValue();
        } else {
            result = value;
        }
        return Double.toString(result);
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
        return "Historique: " + this.history + ", Résultat : " + this.input;
    }
}
