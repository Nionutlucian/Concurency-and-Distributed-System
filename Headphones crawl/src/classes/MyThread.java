package classes;

public class MyThread extends Thread {



    private int thread_index;
    MyLock lock;
    public MyThread(int thread_index,MyLock lock){

        this.thread_index = thread_index;
        this.lock = lock;
    }
    public void run() {
        ParseCel.parseCel(thread_index, lock );
        ParseEmag.parseEmag(thread_index,lock);
          //DownloadPage.downloadCel(thread_index);
          //DownloadPage.downloadEmag(thread_index);
    }
}
