public class TooManyInvalidAttemptsException extends Exception {
    public TooManyInvalidAttemptsException() {
        super("Terlalu banyak percobaan input yang salah!");
    }
}