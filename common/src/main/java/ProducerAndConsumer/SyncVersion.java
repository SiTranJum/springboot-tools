package ProducerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SyncVersion {
    private int MAX_LEN = 10;
    private Queue<String> queue = new LinkedList<>();
    public class Producer extends Thread{
        @Override
        public void run() {
            producer();
        }

        public synchronized void producer() {
            while (true) {
                    while (queue.size() == MAX_LEN) {
                        this.notify();
                        System.out.println("队列已满");
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add("producer 1");
                    System.out.println("生产者生产了一条任务，当前存在任务数为" + queue.size());
                    this.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public class Consumer extends Thread{
        @Override
        public void run() {
            consumer();
        }

        public synchronized void consumer() {
            while (true) {
                    while (queue.size() == 0) {
                        this.notify();
                        System.out.println("队列为空");
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    this.notify();
                    System.out.println("消费者消费了一条记录，当前存在任务数为" + queue.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SyncVersion pc = new SyncVersion();
        Producer producer = pc.new Producer();
        Consumer consumer1 = pc.new Consumer();
        producer.start();
        consumer1.start();
    }
}
