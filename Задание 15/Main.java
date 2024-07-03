import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {

            Source xmlSource = new StreamSource(new File("tariffs.xml"));

            Source xsltSource = new StreamSource(new File("transform.xsl"));

            Result result = new StreamResult(new File("tariffs.html"));

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsltSource);

            transformer.transform(xmlSource, result);

            System.out.println("Трансформация проведена успешно.!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
