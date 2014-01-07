package ni.OraDocletToPlDoc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlFileTest {
   
   private static String ps = File.separator;
   
   private static String htmlFileWithPackageLinks = "testfiles" + ps + "html" + ps + "htmlWithPackageLinks.html";
   private static String htmlFileWithoutPackageLinks = "testfiles" + ps + "html" + ps + "htmlWithoutPackageLinks.html";
   private static String tempHtmlFileWithPackageLinks = "testfiles" + ps + "html" + ps + "_htmlWithPackageLinks.html";
   private static String tempHtmlFileWithoutPackageLinks = "testfiles" + ps + "html" + ps + "_htmlWithoutPackageLinks.html";

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      FileOperations.copyFile(htmlFileWithPackageLinks, tempHtmlFileWithPackageLinks);
      FileOperations.copyFile(htmlFileWithoutPackageLinks, tempHtmlFileWithoutPackageLinks);
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception {
      FileOperations.deleteFile(tempHtmlFileWithPackageLinks);
      FileOperations.deleteFile(tempHtmlFileWithoutPackageLinks);
   }

   @Test
   public void testCreation() {
      try {
         HtmlFile f = new HtmlFile(tempHtmlFileWithoutPackageLinks);
         assertNotNull(f);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         fail("File not found");
      }
   }

   @Test
   public void testUpdateIfNecessaryWhenUpdated() {
      try {
         HtmlFile f = new HtmlFile(tempHtmlFileWithPackageLinks);
         f.updateIfNecessary();
         String oldHtml = FileOperations.fileContents(htmlFileWithPackageLinks);
         String newHtml = FileOperations.fileContents(tempHtmlFileWithPackageLinks);
         assertFalse(oldHtml.equals(newHtml));
         assertTrue(f.isChanged());
      } catch (IOException e) {
         e.printStackTrace();
         fail("IO Error");
      }
   }
   
   @Test
   public void testUpdateIfNecessaryWhenNotUpdated() {
      try {
         HtmlFile f = new HtmlFile(tempHtmlFileWithoutPackageLinks);
         f.updateIfNecessary();
         String oldHtml = FileOperations.fileContents(htmlFileWithoutPackageLinks);
         String newHtml = FileOperations.fileContents(tempHtmlFileWithoutPackageLinks);
         assertTrue(oldHtml.equals(newHtml));
         assertFalse(f.isChanged());
      } catch (IOException e) {
         e.printStackTrace();
         fail("IO Error");
      }
   }
   
}
