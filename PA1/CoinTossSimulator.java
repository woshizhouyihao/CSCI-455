// Name: Yihao Zhou
// USC NetID: 9751577777
// CS 455 PA1
// Fall 2020

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;

public class CoinTossSimulator {
   private int numTrials;
   private int numTwoHeads;
   private int numTwoTails;
   private int numHeadTail;


   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      numTrials = 0;
      numTwoHeads = 0;
      numTwoTails = 0;
      numHeadTail = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      // error checking
      if(numTrials <= 0){
         System.out.println("ERROR: Number entered must be greater than 0.");
      } else{
         // random number generates
         Random r = new Random();
         this.numTrials = this.numTrials + numTrials;
         for(int i = 0; i < numTrials; i++){
            int r1 = r.nextInt(2);
            int r2 = r.nextInt(2);
            if(r1 == 0 && r2 == 0){
               numTwoHeads++;
            } else if(r1 == 1 && r2 == 1){
               numTwoTails++;
            } else{
               numHeadTail++;
            }
         }    
      }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return numTrials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return numTwoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return numTwoTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numHeadTail; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      numTrials = 0;
      numTwoHeads = 0;
      numTwoTails = 0;
      numHeadTail = 0;
   }

}
