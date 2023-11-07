package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

public class Image {
  public static String encodeFileToBase64Binary(String path){
    String encodedFile = null;
    try {
      File file =  new File(path);
      FileInputStream fileInputStreamReader = new FileInputStream(file);
      byte[] bytes = new byte[(int)file.length()];
      fileInputStreamReader.read(bytes);
      encodedFile = new String(Base64.encodeBase64(bytes), "UTF-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return encodedFile;
  }
}
