package com;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread {

    private Semaphore semaphore;

    MyThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while(Main.i < Main.X) {
            try {
                if(Main.i < Main.X) {
                    semaphore.acquire();
                    System.out.println(this.getName() + "a preluat permisiunea");
                    Main.i++;
                    System.out.println(this.getName() + " a incremetat i,i=" + Main.i);
                }
                semaphore.release();
                System.out.println(this.getName() + "a cedat permisiunea");
                }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
