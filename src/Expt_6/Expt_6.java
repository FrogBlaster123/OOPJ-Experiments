interface Shape{
    double area();
    double perimeter();
    String getName();
}

class Circle implements Shape{
    private double radius;

    public Circle(double radius){
        this.radius  = radius;
    }

    @Override
    public double area(){
        return Math.PI * radius * radius;
    }
    @Override
    public double perimeter(){
        return 2 * Math.PI * radius;
    }

    @Override
    public String getName(){
        return "Circle";
    }
}

class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }

    @Override
    public String getName() {
        return "Rectangle";
    }
}

public class Expt_6 {
    public static void main(String[] args){
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        System.out.println(circle.getName() + " Area: " + circle.area());
        System.out.println(circle.getName() + " Perimeter: " + circle.perimeter());

        System.out.println(rectangle.getName() + " Area: " + rectangle.area());
        System.out.println(rectangle.getName() + " Perimeter: " + rectangle.perimeter());
    }
}
