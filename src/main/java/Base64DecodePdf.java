import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

class Base64DecodePdf {

  private static final Logger log = Logger.getLogger(Base64DecodePdf.class.getName());

  public static void main(String[] args) throws Exception {
    log.info("enter path to directory, filename for pdf and filename with base64text");
    log.info("(attention! files must be without extensions) for example:");
    log.info("/User/username/Documents pdfFile textFile");

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String filepath = reader.readLine();
    String pdfFilename = reader.readLine();
    String txtFileName = reader.readLine();
    File file = new File(String.format("%s/%s.pdf", filepath, pdfFilename));

    FileInputStream fis = new FileInputStream(String.format("%s/%s.txt", filepath, txtFileName));
    String stringTooLong = IOUtils.toString(fis, StandardCharsets.UTF_8);

    try ( FileOutputStream fos = new FileOutputStream(file)) {
      byte[] decoder = Base64.getDecoder().decode(stringTooLong);
      fos.write(decoder);
      log.info("PDF File Saved");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}