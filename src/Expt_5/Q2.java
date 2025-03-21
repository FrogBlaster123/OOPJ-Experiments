package Expt_5;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class Employee extends Person {
    double salary;

    Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    void display_Emp() {
        super.display(); // Call the superclass's display() method
        System.out.println("Salary: " + salary);
    }
}

class Manager extends Employee {
    String department;

    Manager(String name, int age, double salary, String department) {
        super(name, age, salary);
        this.department = department;
    }

    void display_Manager() {
        super.display_Emp(); // Call the superclass's display_Emp() method
        System.out.println("Department: " + department);
    }
}

public class Q2 {
    public static void main(String[] args) {
        Employee employee = new Employee("Alice", 30, 50000.0);
        Manager manager = new Manager("Bob", 40, 80000.0, "Sales");

        System.out.println("Employee Details:");
        employee.display();
        employee.display_Emp();

        System.out.println("\nManager Details:");
        manager.display();
        manager.display_Manager();
    }
}