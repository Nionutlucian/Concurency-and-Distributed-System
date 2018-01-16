package classes;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {


    private final Lock lock = new ReentrantLock();
    MyLock() {}

    public void addInList(Product produs){
        HashMap<String, String> map = new HashMap<>();
        boolean add = false;
        lock.lock();

        try {
            if (Main.arrayList.size() > 0){
                for(int i=0;i<Main.arrayList.size();i++) {

                    if (Main.arrayList.get(i).getId().equals(produs.getId())) {
                        Main.arrayList.get(i).link_price.put(produs.getPrice(), produs.getLink());
                        add = true;
                    }

                }
            }
            if(add == false){
                Main.arrayList.add(produs);
            }

        } finally {
            lock.unlock();
        }
    }
}
