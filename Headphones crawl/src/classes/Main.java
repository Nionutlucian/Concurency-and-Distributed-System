package classes;

import classes.MyThread;
import classes.Product;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static ArrayList<Product> arrayList = new ArrayList<>();
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        Product produs1 = new Product("Casti exemplu1","27407126","100.00","www.casti.ro");
        arrayList.add(produs1);
        Product produs2 = new Product("Casti exemplu2","208542","700","www.casti.ro");
        arrayList.add(produs2);

        ArrayList<Thread> threadList = new ArrayList();
        for (int i = 1; i <103; i++) {
            MyThread thread = new MyThread(i,lock);
            thread.start();
            threadList.add(thread);
        }
        for(int i=0;i<threadList.size();i++){
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Product prod : arrayList) {
            System.out.println("Product title:" + prod.getTitle());
            System.out.println("Product id:" + prod.getId());
            /*System.out.println("Product price:" + prod.getPrice());
            System.out.println("Product link:" + prod.getLink());*/

            for(String key : prod.getLink_price().keySet()){
                System.out.println("Product price:" + key);
                System.out.println("Product link:" + prod.getLink_price().get(key));
            }
        }
    System.out.println("Number of products is:" +arrayList.size());


        System.out.println("The list with the smallest price is:");

        for (Product prod : arrayList) {
            System.out.println("Product title:" + prod.getTitle());
            System.out.println("Product id:" + prod.getId());
            //System.out.println("Product price:" + prod.getPrice());
            //System.out.println("Product link:" + prod.getLink());
            double min = 0;
            for(String key : prod.link_price.keySet()) {
                if(Double.parseDouble(key) < min){
                    min = Double.parseDouble(key);
                }
                else {
                    min = Double.parseDouble(key);
                }
            }
                System.out.println("Product price:" + min);
                System.out.println("Product link:" + prod.getLink_price().get(String.format("%.2f",min)));
        }
    }
}
