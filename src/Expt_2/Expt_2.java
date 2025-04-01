import java.util.*;
public class Expt_2{
	public static void main(String[] args){
		// 1. Max, Min, and Total from Command Line
        	if (args.length > 0) {
            	try {
                	int num1 = Integer.parseInt(args[0]);
                	int num2 = Integer.parseInt(args[1]);
                	int num3 = Integer.parseInt(args[2]);

                	int max = Math.max(num1, Math.max(num2, num3));
                	int min = Math.min(num1, Math.min(num2, num3));
                	int total = num1 + num2 + num3;

                	System.out.println("Max: " + max);
                	System.out.println("Min: " + min);
                	System.out.println("Total: " + total);
            	} catch (NumberFormatException e) {
                	System.out.println("Invalid command-line arguments. Please provide three integers.");
            	}
        	} 
		else {
            		System.out.println("Please provide three integers as command-line arguments.");
        	}

        	Scanner input = new Scanner(System.in);

        	// 2. Sum of Digits
        	System.out.print("\nEnter an integer to calculate sum of digits: ");
        	int num = input.nextInt();
        	int sumOfDigits = 0;
        	while (num > 0) {
            		int digit = num % 10;
            		sumOfDigits += digit;
            		num /= 10;
        	}
        	System.out.println("Sum of digits: " + sumOfDigits);

        	// 3. Fibonacci Sequence
        	System.out.print("\nEnter the number of terms for Fibonacci sequence: ");
        	int n = input.nextInt();
        	int first = 0, second = 1;
        	System.out.print("Fibonacci Sequence: ");
        	for (int i = 0; i < n; i++) {
            		System.out.print(first + " ");
            		int next = first + second;
            		first = second;
            		second = next;
        	}
        	System.out.println();

        	// 4. Palindrome Check
        	System.out.print("\nEnter an integer to check for palindrome: ");
        	int number = input.nextInt();
        	int originalNumber = number;
        	int reversedNumber = 0;
        	while (number > 0) {
            		int digit = number % 10;
            		reversedNumber = reversedNumber * 10 + digit;
            		number /= 10;
        	}
        	if (originalNumber == reversedNumber) {
            		System.out.println(originalNumber + " is a palindrome.");
        	} else {
            		System.out.println(originalNumber + " is not a palindrome.");
        	}

        	// 5. Prime Check
        	System.out.print("\nEnter an integer to check for prime: ");
        	int numToCheck = input.nextInt();
        	boolean isPrime = true;
        	if (numToCheck <= 1) {
            		isPrime = false;
        	} else {
            	for (int i = 2; i <= Math.sqrt(numToCheck); i++) {
                	if (numToCheck % i == 0) {
                    		isPrime = false;
                    		break;
                	}
            	}
        	}
        	if (isPrime) {
            		System.out.println(numToCheck + " is a prime number.");
        	} else {
            		System.out.println(numToCheck + " is not a prime number.");
        	}

        	// 6. Armstrong Number Check
        	System.out.print("\nEnter an integer to check for Armstrong number: ");
        	int armstrongNum = input.nextInt();
        	int originalArmstrongNum = armstrongNum;
        	int numDigits = 0;
        	int sumOfPowers = 0;

        	int temp = armstrongNum;
        	while (temp > 0) {
            		numDigits++;
            		temp /= 10;
        	}

        	temp = armstrongNum;
        	while (temp > 0) {
            		int digit = temp % 10;
            		sumOfPowers += Math.pow(digit, numDigits);
            		temp /= 10;
        	}

        	if (originalArmstrongNum == sumOfPowers) {
            		System.out.println(originalArmstrongNum + " is an Armstrong number.");
        	} else {
            		System.out.println(originalArmstrongNum + " is not an Armstrong number.");
        	}

        	// 7. Reverse a Number
        	System.out.print("\nEnter an integer to reverse: ");
        	int numToReverse = input.nextInt();
        	int reversed = 0;
        	while (numToReverse > 0) {
            		int digit = numToReverse % 10;
            		reversed = reversed * 10 + digit;
            		numToReverse /= 10;
        	}
        	System.out.println("Reversed number: " + reversed);

        	// 8. Factorial using Recursion
        	System.out.print("\nEnter an integer to calculate factorial: ");
        	int factNum = input.nextInt();
        	long factorial = calculateFactorial(factNum);
        	System.out.println("Factorial of " + factNum + ": " + factorial);

        	// 9. Prime Numbers in a Range
        	System.out.print("\nEnter the starting number of the range: ");
        	int n1 = input.nextInt();
        	System.out.print("Enter the ending number of the range: ");
        	int n2 = input.nextInt();

        	System.out.println("Prime numbers between " + n1 + " and " + n2 + ":");
        	for (int i = n1; i <= n2; i++) {
            	if (isPrime(i)) {  // Reuse the isPrime function from earlier
                	System.out.print(i + " ");
            	}
        	}
        	System.out.println();
		//10. Pattern
		for(int i = 1; i <= 9; i+=2){
			for(int j = 9; j >= i; j-=2){
				System.out.print(" ");
			}
			for(int k = 1; k <= i; k++){
				System.out.print("*");
			}
			System.out.println();
		}
		//11. Pattern
		for(int i = 1; i <= 9; i+=2){
			for(int j = 9; j >= i; j-=2){
				System.out.print(" ");
			}
			for(int k = 1; k < i/2; k++){
				System.out.print(k);
			}
			for(int l = i/2; l >= 1; l--){
				System.out.print(l);
			}
			System.out.println();
		}
		input.close();

	}
	// Recursive function for factorial
    public static long calculateFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }

    // Helper function to check if a number is prime (reused)
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}