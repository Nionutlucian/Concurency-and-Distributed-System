package classes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ParseEmag {
    public static void parseEmag(int page_index,MyLock lock) {
        Document doc = null;
        Document firstpage = null;
        ArrayList<String> listOfProducts = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.emag.ro/casti-pc/p" + page_index + "/c").get();
            firstpage = Jsoup.connect("https://www.emag.ro/casti-pc/c").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ((firstpage.text().equals(doc.text()) != true && page_index >1) || page_index ==1 ) {

            /////////////Parsarea link-urilor
            Elements links = doc.select("div[class=card-section-mid-inner] h2 a");
            for (Element x : links) {
                String url = x.absUrl("href");
                listOfProducts.add(url);
                //System.out.println(url);
            }
            ////////////////////////////////////

            //////////////Parsarea preturilor////////////
            Elements firstprice = doc.select(" div[class=card-section-btm] p[class=product-new-price]");
            Elements secondprice = doc.select(".product-new-price > sup");
            String first_price_prod = null;
            String second_price_prod = null;
            for (int i = 0; i < firstprice.size(); i++) {
                first_price_prod = firstprice.get(i).ownText();
                first_price_prod = first_price_prod.replace(".","");
                second_price_prod = secondprice.get(i).ownText();
                String price = first_price_prod + "." + second_price_prod;
                listOfProducts.add(price);
                //System.out.println(price);
            }
            ////////////////////////////////////////////////////////

            ////////Parsarea id-urilor
            Elements id = doc.select(".card-footer");
            int duplicateID = 0;
            for (Element x : id) {
                String id_content;
                boolean bool = false;
                if(x.select("input[name=product[]]").isEmpty() && bool == false){
                    id_content = "Nu este in stoc!" + duplicateID;
                    listOfProducts.add(id_content);
                    duplicateID++;
                    bool = true;
                }
               if(bool == false){
                    id_content = x.select("input[name=product[]]").attr("value");
                    listOfProducts.add(id_content);
                }
                //System.out.println(x.select("input[name=product[]]"));
                //System.out.println(x.attr("value").length());
                //System.out.println("aici:" + x);
                //String id_content = x.attr("value");
                //  listOfProducts.add(id_content);
                //}
                //System.out.println(id_content);
                /////////////////////////////
            }
            /////////////Parsarea titlurilor
            Elements title = doc.select("h2 > a");
            for (Element x : title) {
                String title_prod = x.text();
                listOfProducts.add(title_prod);
                //System.out.println(title_prod);
            }
            //////////////////////////////////
            int records_per_page = listOfProducts.size()/4;//numarul de produse de pe pagina

            for(int i = 0;i<records_per_page;i++) {
                String titlu  = listOfProducts.get((i) + records_per_page*3);
                String id_prod = listOfProducts.get((i) + records_per_page*2);
                String pret = listOfProducts.get((i) + records_per_page);
                String link = listOfProducts.get((i));
                Product product = new Product(titlu,id_prod,pret,link);
                lock.addInList(product);
            }
        }

     }
}
