package Postfixx;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PostfixCalculator implements CalculatorInterface {
    private StackInterface<Integer> stack;

    public PostfixCalculator(StackInterface<Integer> stack) {
        this.stack = stack;
    }

    @Override
    public int evaluate(String expression) {
        String[] tokens = expression.split("\\s+");
        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                stack.push(Integer.parseInt(token));
            } else if (token.matches("[+\\-*/]")) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Error: Insuficientes operandos para la operación");
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = performOperation(token.charAt(0), operand1, operand2);
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Error: Token no válido en la expresión: " + token);
            }
        }

        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentException("Error: Expresión incompleta o incorrecta");
        }
    }

    private int performOperation(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Error: División entre cero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Error: Operador no válido: " + operator);
        }
    }

    public static void main(String[] args) {
        System.out.println("Calculadora de expresiones en notación Postfix\n");

        // Crear la implementación deseada de la pila según la entrada del usuario
        StackInterface<Integer> stack;
        switch (args[0]) {
            case "ArrayList":
                stack = new ArrayListStack<>();
                break;
            case "Vector":
                stack = new VectorStack<>();
                break;
            case "SinglyLinkedList":
                stack = new SinglyLinkedList<>();
                break;
            case "DoublyLinkedList":
                stack = new DoublyLinkedList<>();
                break;
            default:
                throw new IllegalArgumentException("Error: Implementación de pila no válida");
        }

        // Crear la calculadora
        PostfixCalculator calculator = new PostfixCalculator(stack);

        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    int result = calculator.evaluate(line.trim());
                    System.out.println("Expresión: " + line.trim() + " = " + result);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
