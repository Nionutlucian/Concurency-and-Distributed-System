package classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class DownloadPage {
    public static void downloadCel(int page_index) {
        Document doc = null;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("page_cel_nr" + page_index, "UTF-8");
            doc = Jsoup.connect("http://www.cel.ro/casti/0a-" + page_index).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(doc);

    }

    public static void downloadEmag(int page_index) {
        Document doc = null;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("page_emag_nr" + page_index, "UTF-8");
            doc = Jsoup.connect("https://www.emag.ro/casti-pc/p" + page_index + "/c").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(doc);

    }
}
