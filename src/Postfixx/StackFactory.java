package Postfixx;

public interface StackFactory {
    static StackInterface<Integer> createStack(String type) {
        switch (type) {
            case "ArrayList":
                return new ArrayListStack<>();
            case "Vector":
                return new VectorStack<>();
            case "SinglyLinkedList":
                return new SinglyLinkedList<>();
            case "DoublyLinkedList":
                return new SinglyLinkedList<>();
            default:
                throw new IllegalArgumentException("Error: Implementación de pila no válida");
        }
    }
}
