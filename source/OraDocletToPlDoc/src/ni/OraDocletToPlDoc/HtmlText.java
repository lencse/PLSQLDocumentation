package ni.OraDocletToPlDoc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlText {

   private String htmlText;
   private boolean changed = false;
   
   public void updateLinks() {
      Document doc = Jsoup.parse(htmlText);
      Elements links = doc.select("a[href]");
      for (Element link : links) {
         updateLinkIfPointsToPackage(link);
      }
      htmlText = doc.html();
   }
   
   private void updateLinkIfPointsToPackage(Element link) {
      String href = link.attr("href");
      if (href.matches("^package-.+html$")) {
         changed = true;
         String newHref = href.replaceFirst("^package-", "pldoc/");
         link.attr("href", newHref);
      }
   }
   
   public HtmlText(String htmlText) {
      this.htmlText = htmlText;
   }
   
   public String getHtmlText() {
      return htmlText;
   }
   
   public boolean isChanged() {
      return changed;
   }
   
}
