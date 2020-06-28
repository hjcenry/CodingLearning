package org.hjc.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * 交替打印FooBar
 *
 * @author 何金成
 * @date 2020/6/28 16:32
 */
public class PrintFooBarAlternately {

    public static void main(String[] args) {
        final PrintFooBarAlternately printFooBarAlternately = new PrintFooBarAlternately(5);
        final Runnable printFoo = new Runnable() {
            public void run() {
                System.out.print("foo");
            }
        };
        final Runnable printBar = new Runnable() {
            public void run() {
                System.out.print("bar");
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    printFooBarAlternately.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    printFooBarAlternately.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
    Suppose you are given the following code:

    class FooBar {
      public void foo() {
        for (int i = 0; i < n; i++) {
          print("foo");
        }
      }

      public void bar() {
        for (int i = 0; i < n; i++) {
          print("bar");
        }
      }
    }
    The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.

     

    Example 1:

    Input: n = 1
    Output: "foobar"
    Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
    Example 2:

    Input: n = 2
    Output: "foobarfoobar"
    Explanation: "foobar" is being output 2 times.

    我们提供一个类：

    class FooBar {
      public void foo() {
        for (int i = 0; i < n; i++) {
          print("foo");
        }
      }

      public void bar() {
        for (int i = 0; i < n; i++) {
          print("bar");
        }
      }
    }
    两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。

    请设计修改程序，以确保 "foobar" 被输出 n 次。

     

    示例 1:

    输入: n = 1
    输出: "foobar"
    解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
    示例 2:

    输入: n = 2
    输出: "foobarfoobar"
    解释: "foobar" 将被输出两次。

    url:https://leetcode-cn.com/problems/print-foobar-alternately/
    */

    private int n;
    private boolean fooTurn = true;
    private Object lock = new Object();

    public PrintFooBarAlternately(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized(lock) {
                if (!fooTurn) {
                    lock.wait();
                }
                fooTurn = false;
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized(lock) {
                if (fooTurn) {
                    lock.wait();
                }
                fooTurn = true;
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                lock.notifyAll();
            }
        }
    }
}
