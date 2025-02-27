import java.util.Scanner;

class Student{
    Scanner input = new Scanner(System.in);
    String name, dob;
    int rollNo;
    public void Inputdata(){
        System.out.print("Enter Name: ");
        name = input.nextLine();
        System.out.print("Enter Roll Number: ");
        rollNo = input.nextInt();
        System.out.print("Enter Date of Birth");
        dob = input.nextLine();
    }
}

class Marks extends Student{
    int[] subjectMarks;
    int total;
    double percentage;
    String grade;

    public void readdata() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        subjectMarks = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            subjectMarks[i] = scanner.nextInt();
        }
    }

    public void compute() {
        total = 0;
        for (int mark : subjectMarks) {
            total += mark;
        }
        percentage = (double) total / subjectMarks.length;

        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }
    }

    public void show() {
        System.out.println("\nStudent Details:");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Date of Birth: " + dob);
        System.out.println("\nSubject Marks:");
        for (int i = 0; i < subjectMarks.length; i++) {
            System.out.println("Subject " + (i + 1) + ": " + subjectMarks[i]);
        }
        System.out.println("\nTotal Marks: " + total);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + grade);
    }
}

class Person{
    String name;
    int age;
    void Display(){
        System.out.println("Name of Person: " + name);
        System.out.println("Age of Person: " + age);
    }
}

class Employee extends Person{

}

public class Expt_5{
    public static void main(String[] args) {
        //Q1
//        Marks studentMarks = new Marks();
//        studentMarks.Inputdata();
//        studentMarks.readdata();
//        studentMarks.compute();
//        studentMarks.show();

        //Q2

    }
}