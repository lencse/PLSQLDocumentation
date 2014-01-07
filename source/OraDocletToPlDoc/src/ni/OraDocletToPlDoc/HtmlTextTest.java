package ni.OraDocletToPlDoc;

import static org.junit.Assert.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class HtmlTextTest {
   
   private String html = ""
      + "<html>\n"
      + "<head>\n"
      + "<title>\n"
      + "Packages\n"
      + "</title>\n"
      + "</head>\n"
      + "<body bgcolor=\"#ffffff\">\n"
      + "<h1>Packages</h1>\n"
      + "<hr>\n"
      + "<a href=\"package-ACQ_MAIN.html\" target=\"Main\"> ACQ_MAIN </a>\n"
      + "<br>\n"
      + "<a href=\"package-DW_DEDUP.html\" target=\"Main\"> DW_DEDUP </a>\n"
      + "<br>\n"
      + "<a href=\"package-DW_DEPENDENCY_UTIL.html\" target=\"Main\"> DW_DEPENDENCY_UTIL </a>\n"
      + "<br>\n"
      + "<a href=\"table-DW_SCHEMAS.html\" target=\"Main\"> DW_SCHEMAS </a>\n"
      + "<br>\n"
      + "<a href=\"table-DW_SCHEMA_ATTRIBUTES.html\" target=\"Main\"> DW_SCHEMA_ATTRIBUTES </a>\n"
      + "<br>\n"
      + "\n"
      + "<br>\n"
      + "</body>\n"
      + "</html>\n";

   private String htmlWithNoPackageLinks = ""
         + "<html>\n"
         + "<head>\n"
         + "<title>\n"
         + "Packages\n"
         + "</title>\n"
         + "</head>\n"
         + "<body bgcolor=\"#ffffff\">\n"
         + "<h1>Packages</h1>\n"
         + "<hr>\n"
         + "<a href=\"table-DW_SCHEMAS.html\" target=\"Main\"> DW_SCHEMAS </a>\n"
         + "<br>\n"
         + "<a href=\"table-DW_SCHEMA_ATTRIBUTES.html\" target=\"Main\"> DW_SCHEMA_ATTRIBUTES </a>\n"
         + "<br>\n"
         + "\n"
         + "<br>\n"
         + "</body>\n"
         + "</html>\n";

   @Test
   public void testCreation() {
      HtmlText h = new HtmlText("Test");
      assertNotNull(h);
   }

   @Test
   public void testGetHtmlText() {
      HtmlText h = new HtmlText("Test");
      assertEquals("Test", h.getHtmlText());
   }
   
   @Test
   public void testUpdateLinks() {
      HtmlText h = new HtmlText(html);
      h.updateLinks();
      Document doc = Jsoup.parse(h.getHtmlText());
      Elements links = doc.select("a[href]");
      assertEquals("pldoc/ACQ_MAIN.html",             links.get(0).attr("href"));
      assertEquals("pldoc/DW_DEDUP.html",             links.get(1).attr("href"));
      assertEquals("pldoc/DW_DEPENDENCY_UTIL.html",   links.get(2).attr("href"));
      assertEquals("table-DW_SCHEMAS.html",           links.get(3).attr("href"));
      assertEquals("table-DW_SCHEMA_ATTRIBUTES.html", links.get(4).attr("href"));
   }

   @Test
   public void testIsChangedWhenPackageLinksExist() {
      HtmlText h = new HtmlText(html);
      h.updateLinks();
      assertTrue(h.isChanged());
   }

   @Test
   public void testIsChangedWhenPackageLinksNotExist() {
      HtmlText h = new HtmlText(htmlWithNoPackageLinks);
      h.updateLinks();
      assertFalse(h.isChanged());
   }
   
   
}
