package application;

public class StudentSubject {
	private String no;
    private String number;
    private String name;

    public StudentSubject(String no, String number, String name) {
        this.no = no;
        this.number = number;
        this.name = name;
    }

    // Getters and setters for each property
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
