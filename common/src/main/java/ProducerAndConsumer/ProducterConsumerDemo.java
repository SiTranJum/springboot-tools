package ProducerAndConsumer;

class ProducterConsumerDemo {
    public static void main(String[] args) {
        Resources r = new Resources();
        Productor pro = new Productor(r);
        Consumer con = new Consumer(r);

        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        t1.start();
        t2.start();
        System.out.println("Hello World!");
    }
}

class Resources {
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void set(String name) {
        if (flag)
            try {
                this.wait();
            } catch (Exception e) {
            }
        this.name = name + "--" + count++;

        System.out.println(Thread.currentThread().getName() + "生产者" + this.name);
        flag = true;
        //唤醒对方进程
        this.notify();

    }

    public synchronized void out() {
        if (!flag)
            try {
                this.wait();
            } catch (Exception e) {
            }

        System.out.println(Thread.currentThread().getName() + " ....消费者...." + this.name);
        flag = false;
        //唤醒对方进程
        this.notify();

    }
}

class Productor implements Runnable {
    private Resources res;

    Productor(Resources res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            res.set("++商品++");
        }
    }

}

class Consumer implements Runnable {
    private Resources res;

    Consumer(Resources res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            res.out();
        }
    }

}