package tutionfeemanagement;

public class Payment {

    int id;
    int totalAmount;
    int paidAmount;
    String date;

    public Payment() {
    }

    public Payment(int id, int totalAmount, int paidAmount, String date) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", totalAmount=" + totalAmount + ", paidAmount=" + paidAmount + ", date=" + date + '}';
    }

}
