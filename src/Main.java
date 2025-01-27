public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        calc.insert("2");
        calc.insert("+");
        calc.insert("4");
        calc.insert("-");
        calc.insert("3");
        calc.insert("3");
        calc.insert("*");
        calc.insert("3");
        calc.insert("+");
        calc.insert("1");
        calc.insert("/");
        calc.insert("4");

        System.out.println("Bouton EQUAL appuyé");
        calc.update(EAction.EQUAL); // -92.75
        System.out.println("Historique: " + calc.getHistory());
        System.out.println("Résultat: " + calc.getResult());

        System.out.println("Bouton CLEAR appuyé");
        calc.update(EAction.CLEAR);
        System.out.println(calc);
    }
}