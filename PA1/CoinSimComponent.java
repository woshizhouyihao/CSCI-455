// Name: Yihao Zhou 
// USC NetID: 9751577777
// CS 455 PA1
// Fall 2020

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * CoinSimComponent class
 * A class extends Jcomponent that creates Bar object with necessary label, width,
 * height, position, scale, color and runs toss coin trials.
 * 
 */
public class CoinSimComponent extends JComponent{
	private int trials;
	private int twoHeads;
	private int twoTails;
	private int oneHeadTail;
		
	// Create a trial component which stores the data from the trials to instance variables
	public CoinSimComponent(int trials){
		this.trials = trials;
		CoinTossSimulator cts = new CoinTossSimulator();
		cts.run(trials);

		twoHeads = cts.getTwoHeads();
		twoTails = cts.getTwoTails();
		oneHeadTail = cts.getHeadTails();

	}

	// this method is to generates 3 bar objects corresponding to two heads, two tails 
	// and one head and tail
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		// current frame dimensions
		int w = getWidth();
		int h = getHeight();
		// margin to top and bottom
		int vb = 40;
		// bar position unit
		int x = w/4;
		// scale based on the frame height
		double scale = (h - 2 * vb)/10.0;
		int barWidth = 60;

		// calculate how many scales needed for the height of bar1
		int barHeight1 = twoHeads * 10/trials;
		// calculate the bottom position
		int barBottom1 = h - vb - barHeight1 * (int) scale;
		// calculate the left position
		int barLeft1 = x - barWidth/2;
		// percent showing on label
		int percent1 =  (int) Math.round(100 * twoHeads/(double) trials);
		String l1 = "Two Heads: " + twoHeads + " (" + percent1 + "%)";
		Bar b1 = new Bar(barBottom1, barLeft1, barWidth, barHeight1, scale, Color.RED, l1);

		// calculate how many scales needed for the height of bar1
		int barHeight2 = oneHeadTail * 10/ trials;
		// calculate the bottom position
		int barBottom2 = h - vb - barHeight2 * (int) scale;
		// calculate the left position
		int barLeft2 = 2 * x - barWidth/2;
		// percent showing on label
		int percent2 =  (int) Math.round(100 * oneHeadTail/(double) trials);
		String l2 = "A Head and a Tail: " + oneHeadTail + " (" + percent2 + "%)";
		Bar b2 = new Bar(barBottom2, barLeft2, barWidth, barHeight2, scale, Color.GREEN, l2);

		// calculate how many scales needed for the height of bar1
		int barHeight3 = twoTails * 10/ trials;
		// calculate the bottom position
		int barBottom3 = h - vb - barHeight3 * (int) scale;
		// calculate the left position
		int barLeft3 = 3 * x - barWidth/2;
		// percent showing on label
		int percent3 =  (int) Math.round(100 * twoTails/(double) trials);
		String l3 = "Two Tails: " + twoTails + " (" + percent3 + "%)";
		Bar b3 = new Bar(barBottom3, barLeft3, barWidth, barHeight3, scale, Color.BLUE, l3);

		b1.draw(g2);
		b2.draw(g2);
		b3.draw(g2);
		
	}
}