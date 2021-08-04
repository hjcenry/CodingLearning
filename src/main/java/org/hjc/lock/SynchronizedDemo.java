package org.hjc.lock;

/**
 * 同步
 *
 * @author 何金成
 * @date 2020/12/14 10:51
 */
public class SynchronizedDemo {

    Object lock = new Object();

    public static void main(String[] args) {
        new SynchronizedDemo().run();
    }

    public void run() {
        int a = 1;
        synchronized (lock) {
            a++;
        }
    }
}
