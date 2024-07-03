import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // Источник XML файла
            Source xmlSource = new StreamSource(new File("tariffs.xml"));
            // Источник XSL файла
            Source xsltSource = new StreamSource(new File("transform.xsl"));
            // Результат будет сохранен в tariffs.html
            Result result = new StreamResult(new File("tariffs.html"));

            // Получаем трансформер
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsltSource);

            // Выполняем трансформацию
            transformer.transform(xmlSource, result);

            System.out.println("Transformation completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}