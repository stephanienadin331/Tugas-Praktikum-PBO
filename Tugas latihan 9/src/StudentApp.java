import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class StudentApp extends JFrame {

    private StudentService service;
    private JTable table;
    private DefaultTableModel model;

    private int page = 1;
    private final int pageSize = 5;

    private boolean searching = false;
    private List<Student> searchResults;

    public StudentApp(StudentService service) {
        this.service = service;
        setTitle("Student Management GUI");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TOP BAR =====
        JPanel top = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        JButton addBtn = new JButton("Add Student");

        top.add(new JLabel("Search Name:"));
        top.add(searchField);
        top.add(searchBtn);
        top.add(addBtn);

        add(top, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(new Object[]{"NPM", "Name", "GPA"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BOTTOM =====
        JPanel bottom = new JPanel(new FlowLayout());
        JButton prevBtn = new JButton("Prev");
        JButton nextBtn = new JButton("Next");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        bottom.add(prevBtn);
        bottom.add(nextBtn);
        bottom.add(updateBtn);
        bottom.add(deleteBtn);

        add(bottom, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());

        searchBtn.addActionListener(e -> {
            String text = searchField.getText().trim();
            if (text.isEmpty()) {
                searching = false;
                loadTable();
            } else {
                searching = true;
                loadSearch(text);
            }
        });

        prevBtn.addActionListener(e -> {
            if (!searching && page > 1) {
                page--;
                loadTable();
            }
        });

        nextBtn.addActionListener(e -> {
            if (!searching) {
                page++;
                loadTable();
            }
        });

        loadTable();
        setVisible(true);
    }

    private void loadTable() {
        if (searching) return; // jangan override hasil search

        List<Student> list = service.getAllSorted();
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, list.size());

        model.setRowCount(0);

        if (start >= list.size()) {
            page = 1;
            start = 0;
            end = Math.min(pageSize, list.size());
        }

        for (int i = start; i < end; i++) {
            Student s = list.get(i);
            model.addRow(new Object[]{s.getNpm(), s.getName(), s.getGpa()});
        }
    }

    private void loadSearch(String keyword) {
        searching = true;
        searchResults = service.searchByName(keyword);

        model.setRowCount(0);

        for (Student s : searchResults) {
            model.addRow(new Object[]{s.getNpm(), s.getName(), s.getGpa()});
        }
    }

    private void addStudent() {
        JTextField f1 = new JTextField();
        JTextField f2 = new JTextField();
        JTextField f3 = new JTextField();

        Object[] msg = {
                "NPM:", f1,
                "Name:", f2,
                "GPA:", f3
        };

        if (JOptionPane.showConfirmDialog(this, msg, "Add Student",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            try {
                service.createStudent(f1.getText(), f2.getText(),
                        Double.parseDouble(f3.getText()));
                searching = false;
                loadTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void updateStudent() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }

        String npm = model.getValueAt(row, 0).toString();

        JTextField f1 = new JTextField(model.getValueAt(row, 1).toString());
        JTextField f2 = new JTextField(model.getValueAt(row, 2).toString());

        Object[] msg = {
                "Name:", f1,
                "GPA:", f2
        };

        if (JOptionPane.showConfirmDialog(this, msg, "Update Student",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            try {
                service.updateStudent(npm, f1.getText(),
                        Double.parseDouble(f2.getText()));
                searching = false;
                loadTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }

        String npm = model.getValueAt(row, 0).toString();

        if (JOptionPane.showConfirmDialog(this,
                "Delete " + npm + "?", "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            service.deleteStudent(npm);
            searching = false;
            loadTable();
        }
    }

    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();
        StudentService service = new StudentService(repo);
        new StudentApp(service);
    }

}
