package unl.cse.overloading;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MomentStats {
	
	public static void main(String args[]) {
		
		List<Double> numbers = Arrays.asList(-6.190000e-1, -1.042570e+0, -2.633440e-1, 
				-7.471450e-1, 2.117680e+0, 2.933850e-1, -7.245130e-1, -9.494510e-1, -1.019190e+0, 
				-9.099270e-1, -2.632650e-1, -1.074240e-1, 1.315500e+0, 6.606400e-2, 8.750930e-1, 
				-9.419930e-1, -1.050610e+0, -1.090710e+0, 3.339850e-1, 7.784820e-1);
		
		//Example: double x = moment(numbers, 1, 2);
		//System.out.println(x);
		System.out.println("Please enter a real number:");
		Scanner reader = new Scanner(System.in);
		double alpha = 0;
		if(reader.hasNext()) {
			alpha = reader.nextDouble();
		}
		
		System.out.println("Please enter a positive integer:");
		int k = 1;
		if(reader.hasNext() && reader.nextInt() > 0) {
			k = reader.nextInt();
		}
		
		reader.close();
		
		
		
		double first = moment(numbers, alpha);
		System.out.println(first);
		
		double second = moment(numbers, k);
		System.out.println(second);
		
		double third = moment(numbers, alpha, k);
		System.out.println(third);
		
		double fourth = moment(numbers);
		System.out.println(fourth);
	}
	
	//parameterizes alpha
	public static double moment(List<Double> numbers, double alpha) {
		int k = 1;
		
		double resultA = 0;
		
		for(Double numi : numbers) {
			resultA += Math.pow(numi - alpha, k);
		}
		
		return resultA * 0.05;
	}
	
	//parameterizes k
	public static double moment(List<Double> numbers, int k) {
		double alpha = 0.0;
		
		double resultK = 0;
		
		for(Double numi : numbers) {
			resultK += Math.pow(numi - alpha, k);
		}
		
		return resultK * 0.05;
	}

	//parameterizes both alpha and k
	public static double moment(List<Double> numbers, double a, int k) {
		double resultAK = 0;
		
		for(Double numi : numbers) {
			resultAK += Math.pow(numi - a, k);
		}
		
		return resultAK * 0.05;
	}

	//parameterizes neither alpha nor k
	public static double moment(List<Double> numbers) {
		int k = 1;
		double alpha = 0.0;
		
		double resultN = 0;
		
		for(Double numi : numbers) {
			resultN += Math.pow(numi - alpha, k);
		}
		
		return resultN * 0.05;
	}
	
}
