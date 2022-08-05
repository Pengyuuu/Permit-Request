package main.java;

public class Request {
    
    private String _date;

    private String _name;

    private String _id;

    private String _class;

    private String _section;

    private String _classNum;

    private String _major;

    private String _note;

    private String _email;

    private String _studentEmail;

    public Request() {

        _date = "";
        _name = "";
        _id = "";
        _class = "";
        _section = "";
        _classNum = "";
        _major = "";
        _note = "";
        _email = "";
        _studentEmail = "";
    }

    public Request(String date, String name, String id, String chosenClass, String section, String classNum, String major, String email,
        String studentEmail) {
        
        _date = date;
        _name = name;
        _id = id;
        _class = chosenClass;
        _section = section;
        _classNum = classNum;
        _major = major;
        _note = "";
        _email = email;
        _studentEmail = studentEmail;
    }

    
    public String getSelectedClass() {

        return _class;
    }

    public void setNote(String message) {
        _note = message;
    }

    @Override
    public String toString() {

        return _date + "," + _name + "," + _id + "," + _class + "," + _section + "," + _classNum + "," + _major + "," + 
            "," + _note + "," + _email + "," + _studentEmail;
    }
}
