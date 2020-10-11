// Name: Yihao Zhou 
// USC NetID: 9751577777
// CS 455 PA1
// Fall 2020

import javax.swing.JFrame;

// class to create a frame where all bars are placed
public class CoinSimViewer{
	public static void main(String[] args){
		JFrame frame = new JFrame();

		frame.setSize(800, 500);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int numTrials = 40000000;
		CoinSimComponent component = new CoinSimComponent(numTrials);
		frame.add(component);

		frame.setVisible(true);
	}
}