package cww526;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class myThread {
    private BlockingQueue<Runnable> queue;
    public myThread(int colPoolSize, int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
        for (int i = 0; i < colPoolSize; i++) {
            new myThread(queue).start();
        }
    }

    private void start() {

    }
    public void execute(Runnable task) throws InterruptedException {
        queue.put(task);
    }

    private static  class  MyThread extends  Thread{
         private BlockingQueue<Runnable> queue;

//         public myThread(ThreadGroup group, String name, BlockingQueue<Runnable> queue) {
//             super(group, name);
//             this.queue = queue;
//         }
         public MyThread(BlockingQueue<Runnable> queue){
             this.queue=queue;
         }

         @Override
         public void run() {
             while (true){
                 Runnable task = null;
                 try {
                     task = queue.take();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 task.run();
             }
         }
     }
    public myThread(BlockingQueue<Runnable> queue) {
    }
}
