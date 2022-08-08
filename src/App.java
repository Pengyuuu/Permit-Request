import main.java.Request;
import main.java.RequestManager;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        ArrayList<Request> output = new ArrayList<>();

        RequestManager requestManager = RequestManager.GetInstance();

        ArrayList<Request> input = requestManager.Read();

        input = requestManager.CheckRestricted(input);

        input = requestManager.NumStart(input);

        output = requestManager.Group(input);

        requestManager.Write(output);
    }
}
