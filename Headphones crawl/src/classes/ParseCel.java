package classes;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ParseCel {
    public static void parseCel(int page_index,MyLock lock) {
        Document doc = null;
        PrintWriter writer = null;
        ArrayList<String> listOfProducts = new ArrayList<>();
        try {
            doc = Jsoup.connect("http://www.cel.ro/casti/0a-" + page_index).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean verify = doc.select(".selectat").isEmpty();
        //System.out.println(verify);

        if (verify != true) {
            /////////////Parsarea link-urilor
            Elements links = doc.select(".productTitle > a");
            for (Element x : links) {
                String url = x.absUrl("href");
                listOfProducts.add(url);
                //System.out.println(url);
            }
            ////////////////////////////////////

            //////////////Parsarea preturilor////////////
            Elements price = doc.select("[itemprop=price]");
            for (Element x : price) {
                String price_prod = x.text();
                price_prod = String.format("%.2f",Double.parseDouble(price_prod));
                listOfProducts.add(price_prod);
                //System.out.println(price_prod);
            }
            ////////////////////////////////////////////////////////

            ////////Parsarea id-urilor
            Elements id = doc.select(".stoc_list > span[id]");
            for (Element x : id) {
                String id_content = x.attr("id");
                id_content = id_content.substring(1,id_content.length()-2);
                listOfProducts.add(id_content);
                //System.out.println(id_content);
            }
            /////////////////////////////


            ////////////Parsarea titlurilor
            Elements title = doc.select("span[itemprop=name]");
            for (Element x : title) {
                String title_prod = x.text();
                listOfProducts.add(title_prod);
                //System.out.println(title_prod);
            }
            ///////////////////////////////

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