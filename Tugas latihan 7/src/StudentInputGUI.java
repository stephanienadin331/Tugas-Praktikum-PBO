import javax.swing.*;
import java.awt.*;

public class StudentInputGUI extends JFrame {

    private int invalidAttempts = 0;

    public StudentInputGUI() {
        setTitle("Student Input");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel lblName = new JLabel("Nama:");
        JTextField txtName = new JTextField();

        JLabel lblAge = new JLabel("Umur:");
        JTextField txtAge = new JTextField();

        JButton btnSubmit = new JButton("Submit");

        add(lblName);
        add(txtName);
        add(lblAge);
        add(txtAge);
        add(btnSubmit);

        btnSubmit.addActionListener(e -> {
            try {
                Student s = validateInput(txtName.getText(), txtAge.getText());

                JOptionPane.showMessageDialog(this, "Sukses membuat Student!");

                txtName.setBackground(Color.WHITE);
                txtAge.setBackground(Color.WHITE);

            } catch (InvalidInputException ex) {
                invalidAttempts++;

                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);

                txtName.setBackground(Color.PINK);
                txtAge.setBackground(Color.PINK);

                if (invalidAttempts >= 3) {
                    try {
                        throw new TooManyInvalidAttemptsException();
                    } catch (TooManyInvalidAttemptsException too) {
                        JOptionPane.showMessageDialog(this, too.getMessage());
                        System.exit(0);
                    }
                }
            }
        });

        setVisible(true);
    }

    private Student validateInput(String name, String ageStr) throws InvalidInputException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Nama tidak boleh kosong.");
        }

        int age;

        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Umur harus angka.");
        }

        if (age <= 0) {
            throw new InvalidInputException("Umur harus lebih dari 0.");
        }

        return new Student(name, age);
    }

    public static void main(String[] args) {
        new StudentInputGUI();
    }
}