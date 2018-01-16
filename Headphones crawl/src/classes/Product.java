package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private String title;
    private String id;
    private String price;
    private String link;

    Product(String title,String id,String price,String link){
        this.title = title;
        this.id = id;
        this.price = price;
        this.link = link;
        this.link_price.put(price, link);
    }

    public HashMap<String, String> getLink_price() {
        return link_price;
    }

    public  HashMap<String,String> link_price = new HashMap<>();
    public void setLink_price(HashMap<String, String> link_price) {
        this.link_price = link_price;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }
}
