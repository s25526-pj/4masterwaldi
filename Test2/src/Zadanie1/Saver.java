package Zadanie1;

@FunctionalInterface
public interface Saver<T> {

    String saveToFile(T object);

}
