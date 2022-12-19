import main.java.Request;
import main.java.RequestManager;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        //One();

        Four();
    }

    public static void One() {

        //ArrayList<Request> output = new ArrayList<>();

        RequestManager requestManager = RequestManager.GetInstance();

        ArrayList<Request> input = requestManager.Read();

        input = requestManager.CheckRestricted(input);

        input = requestManager.CheckReserved(input);

        requestManager.Write(input);
    }

    public static void Four() {

        RequestManager requestManager = RequestManager.GetInstance();

        ArrayList<Request> output = requestManager.Read();

        output = requestManager.CheckRestricted(output);

        output = requestManager.CheckReserved(output);

        output = requestManager.NumStart(output);
        
        output = requestManager.Group(output);
        //System.out.println(output.size());

        requestManager.Write(output);
    }
}
