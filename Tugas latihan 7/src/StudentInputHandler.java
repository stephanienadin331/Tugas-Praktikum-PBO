public class StudentInputHandler {

    private int attempts = 0;

    public Student process(String name, String ageStr)
            throws InvalidInputException, TooManyInvalidAttemptsException {

        try {
            if (name == null || name.trim().isEmpty()) {
                throw new InvalidInputException("Nama kosong!");
            }

            int age = Integer.parseInt(ageStr);

            if (age <= 0) {
                throw new InvalidInputException("Umur tidak valid!");
            }

            attempts = 0;
            return new Student(name, age);

        } catch (InvalidInputException | NumberFormatException e) {

            attempts++;

            if (attempts >= 3) {
                throw new TooManyInvalidAttemptsException();
            }

            throw new InvalidInputException("Input tidak valid.");
        }
    }
}

