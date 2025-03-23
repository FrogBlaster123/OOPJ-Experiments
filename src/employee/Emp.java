package employee;

public class Emp {
    public String name;
    public int emp_id;
    public String category;
    public double basic_pay;
    public double hra;
    public double da;
    public double net_pay;
    public double pf;
    public double gross_pay;
    public double income_tax;
    public double allowance;

    public Emp(String name, int emp_id, String category, double basic_pay) {
        this.name = name;
        this.emp_id = emp_id;
        this.category = category;
        this.basic_pay = basic_pay;
    }

    public void calculatePayroll() {
        hra = 0.10 * basic_pay; // 10% of basic pay
        da = 0.20 * basic_pay;  // 20% of basic pay
        pf = 0.12 * basic_pay; // 12% of basic pay
        allowance = 0.05 * basic_pay; // 5% of basic pay
        gross_pay = basic_pay + hra + da + allowance;
        income_tax = 0.15 * gross_pay; // 15% of gross pay
        net_pay = gross_pay - pf - income_tax;
    }

    public void displayPayroll() {
        System.out.println("Employee ID: " + emp_id);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Basic Pay: " + basic_pay);
        System.out.println("HRA: " + hra);
        System.out.println("DA: " + da);
        System.out.println("PF: " + pf);
        System.out.println("Allowance: " + allowance);
        System.out.println("Gross Pay: " + gross_pay);
        System.out.println("Income Tax: " + income_tax);
        System.out.println("Net Pay: " + net_pay);
    }
}