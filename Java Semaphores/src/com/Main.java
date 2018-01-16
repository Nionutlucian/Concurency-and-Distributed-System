package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static int i = 0;
    public static final int X = 10;
    public static Semaphore semaphore = new Semaphore(1);
    public static List<Thread> threadList = new ArrayList<>();

    public static void main(String[] args) {
        for(int k = 0;k<4;k++){
            MyThread thread = new MyThread(semaphore);
            thread.start();
            threadList.add(thread);
        }
        for(Thread thread : threadList){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("i = " + i);
    }
}
