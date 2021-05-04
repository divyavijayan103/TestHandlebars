import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Practice1 {
    public static String getName(String fileName) {
        String temp[] = fileName.split("\\.");
        return temp[temp.length - 2];
    }

    public static void createTextFile(String path, String fileName, String content) {

        String fullPath = path + File.separator + "TextFiles";
        File theDir = new File(fullPath);
        theDir.mkdirs();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fullPath + File.separator + fileName + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.println(content);
        writer.close();
    }

    public static void convertHtmlToBase64String(String directory, String path, String fileName) {
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
        String encodedString = Base64.getEncoder().encodeToString(html_content.getBytes());
        /*decode the string*/
//                        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//                        String decodedString = new String(decodedBytes);
        createTextFile(directory, getName(fileName), encodedString);
    }

    public static void convertImageToBase64(String directory, String path, String fileName) {
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent);
        createTextFile(directory, getName(fileName), encodedString);
    }

    public static void readFiles(String directory) {
        String path = "";
        String fileName = "";

        Path p = Paths.get(directory);
        try (DirectoryStream<Path> filesList = Files.newDirectoryStream(p)) {
            for (Path file : filesList) {
                if (!Files.isDirectory(file)) {
                    path = file.toString();
                    fileName = file.getFileName().toString();
                    if (path.endsWith(".html")) {
                        convertHtmlToBase64String(directory, path, fileName);
                    } else if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg")) {
                        convertImageToBase64(directory, path, fileName);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot open directory: " + directory);
        }

    }

//    public static void main(String[] args) {
//        String currentDir = System.getProperty("user.dir");
//        readFiles(currentDir + File.separator + "src/HtmlFiles");
//    }
}
