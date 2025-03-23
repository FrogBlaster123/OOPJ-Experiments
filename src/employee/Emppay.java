package employee;

public class Emppay {
    public static void main(String[] args) {
        Emp e = new Emp("John Doe", 101, "Manager", 50000.0);
        e.calculatePayroll();
        e.displayPayroll();
    }
}
