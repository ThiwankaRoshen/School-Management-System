package application;

public class ResultRecord {
	private String no;
    private String marks;

    public ResultRecord(String no, String marks) {
        this.no = no;
        this.marks = marks;
    }

    // Getters and setters for each property
    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMarks() {
        return this.marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
   }
