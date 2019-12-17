/******************************************************************************
 *  Compilation:  javac Homework5.java
 *  Execution:    java Homework
 *
 *  Part 1 asks for the user to select a shape then the specifications and prints
 *  the area and perimeter.
 *  Part 2 asks the user to input the number of shapes to create for each shape
 *  and a max side length then uses randomizers to generate shapes with random
 *  specifications. Shows the user what shapes were created.
 *  Part 3 sorts the shape by area and displays them sorted.
 *
 *  Jackson Evans
 ******************************************************************************/

import java.lang.Math;
import java.util.Scanner;
import java.util.Random;

class Shape{
  //parent class
  public double area;
  public double perimeter;
  public String name;

  public Shape(){}
    //constructor

  public static void sort(Shape[] arr){
    //selection sort
    int pivot = arr.length - 1;
    double maxvalue = arr[pivot].area;
    int maxindex = pivot;

    for(int i = 1; i < arr.length; i++){
      for (int j = 0; j < pivot; j++){
        // if the value at arr[j].area is greater than our pivot
        // switch the value with the pivots value.
        if(arr[j].area > maxvalue){
          maxindex = j;
          maxvalue = arr[j].area;
        }
      }
      Shape temp = arr[pivot];
      arr[pivot] = arr[maxindex];
      //switching the element at pivot with element at maxindex
      arr[maxindex] = temp;
      pivot--;
      maxindex = pivot;
      maxvalue = arr[pivot].area;
    }
  }
}

class Triangle extends Shape{
  //triangle subclass
  public double height;
  public double width;

  public Triangle(double h, double w){
    //constructor
    height = h;
    width = w;
    name = "Triangle";

  }

  public void areaTriangle(){
    //method for area
    area = height*width/2;
  }
  public void perimeterTriangle(){
    //method for perimeter
    perimeter = Math.sqrt(Math.pow(height,2) + Math.pow(width,2)) + width + height;
  }
  public String toString(){
    return(name + " with height: "+height + ", width: " + width);
  }
}

class Circle extends Shape{
  //shape subclass
  public double diameter;

  public Circle(double d){
    //constructor
    diameter = d;
    name = "Circle";
  }

  public void areaCircle(){
    //method for area
    area = Math.PI * Math.pow(diameter/2,2);
  }

  public void perimeterCircle(){
    //method for perimeter
    perimeter = Math.PI * diameter;
  }
  public String toString(){
    return(name + " of diameter "+ diameter);
  }
}

class Square extends Shape{
  //square subclass
  public double length;

  public Square(double l){
    //constructor
    length = l;
    name = "Square";
  }

  public void areaSquare(){
    //method for area
    area = length*length;
  }
  public void perimeterSquare(){
    //method for perimeter
    perimeter = 4*length;
  }
  public String toString(){
    return(name + " with sides "+ length);
  }
}

class Rectangle extends Shape{
  //rectangle subclass
  public double height;
  public double width;

  public Rectangle(double h, double w){
    //constructor
    height = h;
    width = w;
    name = "Rectangle";
  }

  public void areaRectangle(){
    //method for area
    area = height*width;
  }
  public void perimeterRectangle(){
    //method for perimeter
    perimeter = 2*height + 2*width;
  }
  public String toString(){
    return(name + " with height: "+height + ", width: " + width);
  }
}

class Trapezoid extends Shape{
  //trapezoid subclass
  public double base;
  public double top;
  public double height;
  public double side;

  public Trapezoid(double b, double t, double h, double s){
    //constructor
    base = b;
    top = t;
    height = h;
    side = s;
    name = "Trapezoid";
  }

  public void areaTrapezoid(){
    //method for area
    area = (base+top)*(height/2);
  }
  public void perimeterTrapezoid(){
    //method for perimeter
    perimeter = base + top + 2*side;
  }
  public String toString(){
    return(name + " where base A: "+ base + ", base B: " + top + ", height: "  + side);
  }
}

public class Homework5{
  //only public class

  public static void main(String[]args){
    //main function
      String strshape;
      System.out.println("Part 1");
      System.out.println("Select a shape");
      System.out.printf("1: Triangle \n2: Rectangle \n3: Square \n4: Circle \n5: Trapezoid \n");
      System.out.println("Enter a value: ");
      Scanner sc = new Scanner(System.in);
      int num = sc.nextInt();
      switch(num){
        //switch functionality
      case 1:
        System.out.println("Enter the height of the triangle: ");
        double t_height = sc.nextDouble();
        System.out.println("Enter the base of the triangle: ");
        double t_width = sc.nextDouble();
        Triangle t = new Triangle(t_height,t_width);
        t.areaTriangle();
        t.perimeterTriangle();
        //updating the objects are and perimeter
        System.out.printf("Triangle: Area = %.2f, Perimteter = %.2f",t.area,t.perimeter);
        break;

      case 2:
        System.out.println("Enter the height of the rectangle: ");
        double r_height = sc.nextDouble();
        System.out.println("Enter the width of the rectangle: ");
        double r_width = sc.nextDouble();
        Rectangle r = new Rectangle(r_height,r_width);
        r.areaRectangle();
        r.perimeterRectangle();
        System.out.printf("Rectangle: Area = %.2f, Perimteter = %.2f",r.area,r.perimeter);
        break;

      case 3:
        System.out.println("Enter the length of the square: ");
        double s_length = sc.nextDouble();
        Square s = new Square(s_length);
        s.areaSquare();
        s.perimeterSquare();
        System.out.printf("Square: Area = %.2f, Perimteter = %.2f",s.area,s.perimeter);
        break;

      case 4:
        System.out.println("Enter the diameter of the circle: ");
        double c_diameter = sc.nextDouble();
        Circle c = new Circle(c_diameter);
        c.areaCircle();
        c.perimeterCircle();
        System.out.printf("Circle: Area = %.2f, Perimteter = %.2f",c.area,c.perimeter);
        break;

      case 5:
        System.out.println("Enter the base of the trapezoid: ");
        double tr_base = sc.nextDouble();
        System.out.println("Enter the top of the trapezoid: ");
        double tr_top = sc.nextDouble();
        System.out.println("Enter the height of the trapezoid: ");
        double tr_height = sc.nextDouble();
        System.out.println("Enter the side of the trapezoid: ");
        double tr_side = sc.nextDouble();
        Trapezoid tr = new Trapezoid(tr_base,tr_top,tr_height,tr_side);
        tr.areaTrapezoid();
        tr.perimeterTrapezoid();
        System.out.printf("Trapezoid: Area = %.2f, Perimteter = %.2f",tr.area,tr.perimeter);
        break;

      default:
        strshape = "null";
        System.out.println("Invalid input");
      }
      System.out.println();

      System.out.println("Part 2");
      Random rand = new Random();
      System.out.println("Enter the number of shapes to create for shape.");
      System.out.println("Triangle: ");
      int num_t = sc.nextInt();
      System.out.println("Rectangle: ");
      int num_r = sc.nextInt();
      System.out.println("Square: ");
      int num_s = sc.nextInt();
      System.out.println("Circle: ");
      int num_c = sc.nextInt();
      System.out.println("Trapezoid: ");
      int num_tr = sc.nextInt();
      Shape[] shapes = new Shape[num_t + num_r + num_s + num_c + num_tr];
      System.out.println("Enter a max side length: ");
      int max_len = sc.nextInt();
      System.out.println("\nThe following shapes were created.");
      Random rand2 = new Random();
      int count = 0;
      //iterates through array
      double total_area = 0;
      double total_perimeter = 0;
      for(int i = 0;i<num_t;i++){
        double h = rand2.nextInt(max_len) + 1;
        double w = rand2.nextInt(max_len) + 1;
        Triangle t = new Triangle(h,w);
        t.areaTriangle();
        t.perimeterTriangle();
        total_area+=t.area;
        total_perimeter+=t.perimeter;
        shapes[count] = t;
        count++;
        System.out.printf("Triangle %.2f by %.2f. Area: %.2f. Perimeter: %.2f. \n",h,w,t.area,t.perimeter);
      }
      for(int i = 0;i<num_r;i++){
        double h = rand2.nextInt(max_len) + 1;
        double w = rand2.nextInt(max_len) + 1;
        Rectangle r = new Rectangle(h,w);
        r.areaRectangle();
        r.perimeterRectangle();
        total_area+=r.area;
        total_perimeter+=r.perimeter;
        shapes[count] = r;
        count++;
        System.out.printf("Rectangle %.2f by %.2f. Area: %.2f. Perimeter: %.2f. \n",h,w,r.area,r.perimeter);
      }
      for(int i = 0;i<num_s;i++){
        double l = rand2.nextInt(max_len) + 1;
        Square s = new Square(l);
        s.areaSquare();
        s.perimeterSquare();
        total_area+=s.area;
        total_perimeter+=s.perimeter;
        shapes[count] = s;
        count++;
        System.out.printf("Square %.2f by %.2f. Area: %.2f. Perimeter: %.2f. \n",l,l,s.area,s.perimeter);
      }
      for(int i = 0;i<num_c;i++){
        double d = rand2.nextInt(max_len) + 1;
        Circle c = new Circle(d);
        c.areaCircle();
        c.perimeterCircle();
        total_area+=c.area;
        total_perimeter+=c.perimeter;
        shapes[count] = c;
        count++;
        System.out.printf("Circle diameter %.2f. Area: %.2f. Perimeter: %.2f. \n",d,c.area,c.perimeter);
      }
      for(int i = 0;i<num_tr;i++){
        double base = rand2.nextInt(max_len) + 1;
        double top = rand2.nextInt(max_len) + 1;
        double height = rand2.nextInt(max_len) + 1;
        double side = rand2.nextInt(max_len) + 1;
        Trapezoid tr = new Trapezoid(base, top, height, side);
        tr.areaTrapezoid();
        tr.perimeterTrapezoid();
        total_area+=tr.area;
        total_perimeter+=tr.perimeter;
        shapes[count] = tr;
        count++;
        System.out.printf("Trapezoid where base A: %.2f, base B: %.2f, height: %.2f, side: %.2f. Area: %.2f. Perimeter: %.2f. \n",base,top,height,side,tr.area,tr.perimeter);
        System.out.printf("\nTotal Area: %.2f\n", total_area);
        System.out.printf("Total Perimeter: %.2f\n", total_perimeter);
      }
      System.out.println("\nPart 3");
      System.out.println("Here are the shapes sorted by area.");
      Shape.sort(shapes);
      //sorting the array by area
      for(int i=0;i<num_t + num_r + num_s + num_c + num_tr;i++){
        System.out.println(shapes[i].toString());
      }
    }
}
