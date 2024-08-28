package application;

public class Student {
    private String number;
    private String name;
    private String regYear;
    private String regGrade;
    private String[] activities;
    private int cnt;
    

    public Student(String number, String name, String regYear,String regGrade) {
        this.regYear = regYear;
        this.regGrade = regGrade;
        this.number = number;
        this.name = name;
        this.activities = new String[4];
        this.cnt = 0;
    }

    

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public String[] getActivites() {
        return this.activities;
    }

    public void setActivities(String act) {
        this.activities[this.cnt] = act;
        cnt++;
    }
    public void resetActivities() {
    	this.activities = new String[4];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getRegYear() {
		return regYear;
	}

	public void setRegYear(String regYear) {
		this.regYear = regYear;
	}

	public String getRegGrade() {
		return regGrade;
	}

	public void setRegGrade(String regGrade) {
		this.regGrade = regGrade;
	}
}

