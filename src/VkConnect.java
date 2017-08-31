import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Азат on 27.08.2017.
 */
public class VkConnect {

    public static void main(String[] args) {
        User user = new User(123);
        user.putNewAction("1");
        user.putNewAction("2");
        user.putNewAction("3");
        System.out.println(user.getActionsInJson());
    }

    VkConnect connect = new VkConnect();
    HttpServer vkHttpServer = null;
    private VkConnect() {
        try {
            vkHttpServer = new HttpServer(this);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public VkConnect getInstance(){
        return connect;
    }

    public String getAction(String urlData){
        return "play";
    }

    public String[] getCurrentUsers(){
        return new String[1];
    }

    public void putNewCurrentUser(){

    }

    public void deleteNewCurrentUser(){

    }

    static class User{
        private int id;
        private LinkedList<String> actionList = new LinkedList<>();

        User(int id){
            this.id = id;
        }
        public
        int getId(){
            return id;
        }

        public void putNewAction(String action){
            actionList.add(action);
        }

        public String getActionsInJson(){
            if(actionList.isEmpty()){
                return "[]";
            }
            String returnedStr = "[";
            for (String action : actionList){
                returnedStr = returnedStr + action + ",";
            }
            returnedStr = returnedStr.substring(0, returnedStr.length() - 1); // костыль!!!
            returnedStr = returnedStr + "]";
            return returnedStr;
        }

    }
}
