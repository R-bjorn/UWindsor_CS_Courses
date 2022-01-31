/**
   A class to simulate a combination lock.
*/
public class ComboLock {
   private int secret1;
   private int secret2;
   private int secret3;

   // 0: nothing entered, 1: secret1 done, 2: secret2 done, 
   // 3: secret3 done and unlocked
   private int lockState;

   private int currentNumber;
   private boolean validSoFar;

   /**
      Initializes the combination of the lock.
      @param secret1 first number to turn right to
      @param secret2 second number to turn left to
      @param secret3 third number to turn right to
   */
   public ComboLock(int secret1, int secret2, int secret3) {
      this.secret1 = secret1;
      this.secret2 = secret2;
      this.secret3 = secret3;
      lockState = 0;
      currentNumber = 0;
      validSoFar = true;
   }

   /**
      Resets the state of the lock so that it can be opened again.
   */
   public void reset() {
      lockState = 0;
      currentNumber = 0;
      validSoFar = true;
   }

   /**
      Turns lock left given number of ticks.
      @param ticks number of ticks to turn left
   */
   public void turnLeft(int ticks) {
      // State 1 is the only valid state in which to turn the lock
      if (lockState == 1) {
         if (ticks == secret2) {
            lockState = 2;
         }
      }
      else {
         validSoFar = false;
      }
   }

   /**
      Turns lock right given number of ticks
      @param ticks number of ticks to turn right
   */
   public void turnRight(int ticks) {
      // States 0 and 2 are the only valid states in which to turn the lock
      if (lockState == 0) {
         if (ticks == secret1) {
            lockState = 1;
         }
      }
      else if (lockState == 2) {
         if (ticks == secret3) {
            lockState = 3;
         }
      }
      else {
         validSoFar = false;
      }
   }

   /**
      Returns true if the lock can be opened now
      @return true if lock is in open state
   */
   public boolean open() {
      return lockState == 3 && validSoFar;
   }
}
