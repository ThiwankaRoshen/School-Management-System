package application;

public class NoticeRecord {

    private String name;
    private String date;

    public NoticeRecord(String name, String date) {
     
        this.name = name;
        this.date = date;
  
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
