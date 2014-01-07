package ni.OraDocletToPlDoc;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HtmlFile {
   
   private String filename;
   private HtmlText htmlText;

   public HtmlFile(String filename) throws FileNotFoundException {
      this.filename = filename;
      String html = FileOperations.fileContents(filename);
      htmlText = new HtmlText(html);
   }
   
   public void updateIfNecessary() throws IOException {
      htmlText.updateLinks();
      if (htmlText.isChanged()) {
         FileOperations.writeFile(filename, htmlText.getHtmlText());
      }
   }
   
   public boolean isChanged() {
      return htmlText.isChanged();
   }

}
