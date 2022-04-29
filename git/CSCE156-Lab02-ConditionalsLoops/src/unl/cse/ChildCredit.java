package unl.cse;

public class ChildCredit {

	public static void main(String args[]) {
		Child tom = new Child("Tommy", 14);
		Child dick = new Child("Richard", 12);
		Child harry = new Child("Harold", 21);
		
		Child arr[] = new Child[3];
		arr[0] = tom;
		arr[1] = dick;
		arr[2] = harry;

		//TODO: write a loop to iterate over the elements in the child array 
		//      and output a table as specified
		System.out.printf("Child	Credit\n");
		double credit;
		double totalCredit = 0;
		if(arr[0].getAge() < 18) {
			credit = 1000;
			totalCredit += credit;
			System.out.printf("%s	$%.2f\n", arr[0], credit);
		} else {
			credit = 0;
			totalCredit += credit;
			System.out.printf("%s	$%.2f\n", arr[0], credit);
		}
		for(int i=1; i<arr.length; i++) {
			if(arr[i].getAge() < 18) {
				credit = 500;
				totalCredit += credit;
				System.out.printf("%s	$%.2f\n", arr[i], credit);
			} else {
				credit = 0;
				totalCredit += 0;
				System.out.printf("%s	$%.2f\n", arr[0], credit);
			}
		}
		System.out.printf("Total Credit:	$%.2f", totalCredit);
	}
}
