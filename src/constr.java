/**
Constructors:
1) Special method
2) Do not have any return type
3) Use: value assignment/initialization
4) Has to be associated without a class
5) Name of constructor is same as the class
6) Can only be public
7) There can be multiple constructors in a class
8) whenever an object of a class is created a call to its relevant constructor is made
*/

public class constr{
	int a,b;
	constr(){
		a = 5;
		b = 20;
	}
	
	constr(int x, int y){
		a = x;
		b = y;
	}

	public static void main(String[] args){
		
		constr abc = new constr();
		constr xyz = new constr(2,3);

		System.out.println(abc.a + " " + abc.b);
		System.out.println(xyz.a + " " + xyz.b);
	}
}