public class Main {
    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();
        StudentService service = new StudentService(repo);
        StudentController controller = new StudentController(service);
        // contoh data awal
        service.createStudent("210101","Siti",3.75);
        service.createStudent("210102","Agus",3.20);
        controller.start();
    }
}
