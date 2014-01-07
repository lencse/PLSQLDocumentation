package ni.OraDocletToPlDoc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DirectoryWalkerTest {
   
   private static String ps = File.separator;

   @Test
   public void testGetDirectoryList() throws FileNotFoundException {
      DirectoryWalker walker = new DirectoryWalker("testfiles" + ps + "dir");
      List<File> files = walker.getDirectoryList();
      List<String> filenames = new ArrayList<String>();
      for (File file : files) {
         filenames.add(file.getPath());
      }
      assertEquals(2, filenames.size());
      assertTrue(filenames.contains("testfiles" + ps + "dir" + ps + "ni"));
      assertTrue(filenames.contains("testfiles" + ps + "dir" + ps + "ni"));
      
   }
   
   @Test
   public void testGetFileList() throws FileNotFoundException {
      DirectoryWalker walker = new DirectoryWalker("testfiles" + ps + "dir" + ps + "engine");
      List<File> files = walker.getFileList();
      List<String> filenames = new ArrayList<String>();
      for (File file : files) {
         filenames.add(file.getPath());
      }
      assertEquals(2, filenames.size());
      assertTrue(filenames.contains("testfiles" + ps + "dir" + ps + "engine" + ps + "index.html"));
      assertTrue(filenames.contains("testfiles" + ps + "dir" + ps + "engine" + ps + "indexes-list.html"));
   }
   

}
