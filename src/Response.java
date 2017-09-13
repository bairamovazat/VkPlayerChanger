import java.util.HashMap;

/**
 * Created by Азат on 11.09.2017.
 */
public interface Response {
    public String getVkResponse(HashMap<String,String> string);
    public String getTelegramResponse(String string);
}
