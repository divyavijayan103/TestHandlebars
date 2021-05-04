import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Practice3 {
    public static void main(String[] args) {
        Pattern cloneUrl = Pattern.compile("https://github.com/(.*)/(.*?).git");
        Matcher m = cloneUrl.matcher("https://github.com/My-Project-Portfolio/backend-authentication.git");
        System.out.println(m.matches());


    }
}
