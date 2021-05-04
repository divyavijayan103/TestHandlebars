import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Practice2 {
    public static String readFile(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String str;
            while ((str = reader.readLine()) != null) {
                contentBuilder.append(str);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return contentBuilder.toString();
    }

    public static String convertHtmlToString(String path) {
        String html_content = "";
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String str;
            while ((str = reader.readLine()) != null) {
                contentBuilder.append(str);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        html_content = contentBuilder.toString();
        return html_content;

    }

    public static JSONObject readJSON(String filename){
        JSONObject jo = null;
        try {
            Object obj = new JSONParser().parse(new FileReader(filename));
            jo = (JSONObject) obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  jo;
    }
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String htmlCode = convertHtmlToString(currentDir + File.separator + "src/main/java/HtmlFiles/Template2.html");
        Handlebars handlebars = new Handlebars();
        try {
            Template template = handlebars.compileInline(htmlCode);
            String obj = convertHtmlToString(currentDir + File.separator + "src/main/java/Data.json");
            String templateString = template.apply(obj);
            System.out.println(templateString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
