package application;

public class PersonRecord {
    private String no;
    private String number;
    private String name;
    

    public PersonRecord(String no, String number, String name) {
        this.no = no;
        this.number = number;
        this.name = name;
        
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

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}

