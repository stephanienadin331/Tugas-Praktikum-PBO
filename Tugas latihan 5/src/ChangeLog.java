public class ChangeLog {
    private final String action;
    private final Student snapshot;
    private final long timestamp;

    public ChangeLog(String action, Student student) {
        this.action = action;
        this.snapshot = new Student(student);
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format(
                "Aksi : %s\n" +
                        "ID   : %d\n" +
                        "Name : %s\n" +
                        "Age  : %d\n" +
                        "Time : %d\n",
                action,
                snapshot.getId(),
                snapshot.getName(),
                snapshot.getAge(),
                timestamp
        );
    }

}

