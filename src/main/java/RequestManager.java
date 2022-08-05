package main.java;

import java.util.ArrayList;

public class RequestManager {
    
    private RequestDAO _dao;

    private ArrayList<String> restricted;

    private static RequestManager _requestManager;

    private ArrayList<Request> sortedList;

    public RequestManager() {

        _dao = RequestDAO.getInstance();

        sortedList = new ArrayList<Request>();
    }

    public static RequestManager GetInstance() {

        if (_requestManager == null) { _requestManager = new RequestManager(); }

        return _requestManager;
    }

    public ArrayList<Request> Read() { return _dao.Read(); }

    public void Write(ArrayList<Request> requests) { _dao.Write(requests); }

    public ArrayList<Request> GetSortedList() { return sortedList; }

    public ArrayList<Request> CheckRestricted(ArrayList<Request> output) {

        restricted = _dao.ReadRestricted();

        for (Request line : output) {

            if (IsAlpha(line.getSelectedClass().charAt(0))) {

                for (String course : restricted) {

                    if (line.getSelectedClass().contains(course)) { line.setNote("Restricted course"); }
                }
            }
        }

        return output;
    }

    public static boolean IsAlpha(char letter) { return letter >= 'A' && letter <= 'Z'; }

    public static int IndexAt(String word) {

        int index = 0;

        for (int i = 0; i < word.length(); i++) {

            if(!IsAlpha(word.charAt(i))) {

                index = i;

                return index;
            }
        }

        return index;
    }

    public ArrayList<Request> NumStart(ArrayList<Request> output) {

        // If student did not correctly put the name of the class, just remove and add it to the sorted list
        for (Request line : output) {

            System.out.println(!IsAlpha(line.getSelectedClass().charAt(0)));

            if (!IsAlpha(line.getSelectedClass().charAt(0))) {

                sortedList.add(line);

                output.remove(line);
            }
        }

        return output;
    }

    public ArrayList<Request> Group(ArrayList<Request> output) {

        while (output.size() != 0) {
            
            sortedList.add(output.get(0));

            output.remove(0);
        }

        return sortedList;
    }
}
