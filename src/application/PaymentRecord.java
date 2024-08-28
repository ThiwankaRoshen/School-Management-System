package application;

public class PaymentRecord {
	private int invoice;
    private String name;
    private String date;
    private int amount;

    public PaymentRecord(int invoice, String name, String date , int amount) {
        this.invoice = invoice;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    // Getters and setters for each property
    public int getInvoice() {
        return invoice;
    }

    public void setinvoice(int invoice) {
        this.invoice = invoice;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
