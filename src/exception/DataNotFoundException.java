package exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("Данных нет.");
    }
}
