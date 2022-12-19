package main.java;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestManager {
    
    private RequestDAO _dao;

    private ArrayList<String> restricted;

    private static RequestManager _requestManager;

    private ArrayList<Request> sortedList;

    private HashMap<String, String> contacts;

    private HashMap<String, String> reserved;

    public RequestManager() {

        _dao = RequestDAO.getInstance();

        sortedList = new ArrayList<Request>();

        contacts = _dao.ReadContacts();

        restricted = _dao.ReadRestricted();

        reserved = _dao.ReadReserved();
    }

    public static RequestManager GetInstance() {

        if (_requestManager == null) { _requestManager = new RequestManager(); }

        return _requestManager;
    }

    public ArrayList<Request> Read() { return _dao.Read(); }

    public void Write(ArrayList<Request> requests) { _dao.Write(requests); }

    public ArrayList<Request> GetSortedList() { return sortedList; }

    public ArrayList<Request> CheckRestricted(ArrayList<Request> output) {

        //restricted = _dao.ReadRestricted();

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

    /**
     * Finds the index of when the class numbering starts
     * @param word  Class title to be checked
     * @return      Index of when the 3 digit number starts
     */
    public static int IsNum(String word) { 
        
        for (int i = 0; i < word.length(); i++) {

            if (Character.isDigit(word.charAt(i))) {

                return i;
            }
        }

        return 0;
    }

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
        for (int i = output.size() - 1; i >= 0; i--) {
            
            if (!IsAlpha(output.get(i).getSelectedClass().charAt(0))) {

                sortedList.add(output.get(i));

                output.remove(output.get(i));
            }
        }
        
        for (int i = output.size() - 1; i >= 0; i--) {

                sortedList.add(output.get(i));

                output.remove(i);
        }

        return sortedList;
    }

    public ArrayList<Request> Group(ArrayList<Request> output) {

        // Have to make new arraylist because sortedList.clear() clears output 
        ArrayList<Request> sortedList = new ArrayList<>();

        while (output.size() != 0) {

            sortedList.add(output.get(0));

            output.remove(0);

            String item = sortedList.get(sortedList.size() - 1).getSelectedClass();

            item = item.length() <= 5 ? item.substring(0, 1) 
                : item.substring(0, IsNum(item));

            String contact = contacts.get(item);

            for (int i = output.size() - 1; i >= 0; i--) {

                /* if (item.length() <= 5) { item = item.substring(0, 1); }

                else { item = item.substring(0, 2); } */

                // if getselectedclass size is <= 5, then substring 0 to 1

                String requestItem = output.get(i).getSelectedClass();

                requestItem = requestItem.length() <= 5 ? requestItem.substring(0, 1) 
                    : requestItem.substring(0, IsNum(requestItem));

                String requestContact = contacts.get(requestItem);

                if (contact == requestContact) {

                    sortedList.add(output.get(i));

                    output.remove(i);
                }
            }
        }

        return sortedList;
    }

    public ArrayList<Request> CheckReserved(ArrayList<Request> output) {

        for (Request line : output) {

            if (reserved.containsKey(line.getClassNum())) {

                if (!(line.getMessage().length() > 0)) { line.setNote(reserved.get(line.getClassNum())); }
            }
        }

        return output;
    }
}
