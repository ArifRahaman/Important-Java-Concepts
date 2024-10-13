class Counter {
   private int count = 0;

   public void increment() {
       count++;
   }

   public int getCount() {
       return count;
   }
}

class Incrementer extends Thread {
   private Counter counter;

   public Incrementer(Counter counter) {
       this.counter = counter;
   }

   @Override
   public void run() {
       for (int i = 0; i < 1000; i++) {
           counter.increment();
       }
   }
}

public class RaceConditionDemo {
   public static void main(String[] args) throws InterruptedException {
       Counter counter = new Counter();

       Incrementer thread1 = new Incrementer(counter);
       Incrementer thread2 = new Incrementer(counter);

       thread1.start();
       thread2.start();

       thread1.join();
       thread2.join();

       System.out.println("Final count: " + counter.getCount());
   }
}
