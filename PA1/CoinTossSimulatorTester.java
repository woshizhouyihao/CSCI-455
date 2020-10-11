// Name: Yihao Zhou 
// USC NetID: 9751577777
// CS 455 PA1
// Fall 2020

import java.util.Scanner;

// tester for CoinTossSimulator
public class CoinTossSimulatorTester{
	public static void main(String[] args){
		// Test start
		CoinTossSimulator test = new CoinTossSimulator();
		System.out.println("After constructor");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " 
								+ (test.getNumTrials() 
								== test.getTwoHeads() 
								+ test.getTwoTails() 
								+ test.getHeadTails()));
		System.out.println();

		// run 1
		test.run(1);
		System.out.println("After run(1)");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " 
								+ (test.getNumTrials() 
								== test.getTwoHeads() 
								+ test.getTwoTails() 
								+ test.getHeadTails()));
		System.out.println();

		// run 10
		test.run(10);
		System.out.println("After run(10)");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " 
								+ (test.getNumTrials() 
								== test.getTwoHeads() 
								+ test.getTwoTails() 
								+ test.getHeadTails()));
		System.out.println();

		// run 100
		test.run(100);
		System.out.println("After run(100)");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " 
								+ (test.getNumTrials() 
								== test.getTwoHeads() 
								+ test.getTwoTails() 
								+ test.getHeadTails()));
		System.out.println();
		
		// reset
		test.reset();
		System.out.println("After reset:");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " 
								+ (test.getNumTrials() 
								== test.getTwoHeads() 
								+ test.getTwoTails() 
								+ test.getHeadTails()));
		System.out.println();

		// run 1000
		test.run(1000);
		System.out.println("After run(1000)");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " 
								+ (test.getNumTrials() 
								== test.getTwoHeads() 
								+ test.getTwoTails() 
								+ test.getHeadTails()));

		// reset
		test.reset();
		System.out.println("After reset:");
		System.out.println("Number of trials [exp:0]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		System.out.println("Tosses add up correctly? " 
								+ (test.getNumTrials() 
								== test.getTwoHeads() 
								+ test.getTwoTails() 
								+ test.getHeadTails()));
		System.out.println();

		// Error checking
		// invalid input
		int i = -1;
		System.out.print("Enter number of trials: " + i + "\n");
		test.run(i);
		// invalid input
		i = 0;
		System.out.print("Enter number of trials: " + i + "\n");
		test.run(i);
		i = 100;
		System.out.print("Enter number of trials: " + i + "\n");
		test.run(i);

		
	}

		

}