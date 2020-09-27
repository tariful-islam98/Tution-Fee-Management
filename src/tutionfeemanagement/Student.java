package tutionfeemanagement;

public class Student {

    private int id;
    private String name;
    private int stdClass;
    private int scholership;
    private int totalAmount;
    private int paidAmount;
    private int due;

    public Student() {
    }

    public Student(int id, String name, int stdClass, int scholership, int totalAmount, int paidAmount, int due) {
        this.id = id;
        this.name = name;
        this.stdClass = stdClass;
        this.scholership = scholership;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.due = due;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStdClass() {
        return stdClass;
    }

    public void setStdClass(int stdClass) {
        this.stdClass = stdClass;
    }

    public int getScholership() {
        return scholership;
    }

    public void setScholership(int scholership) {
        this.scholership = scholership;
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

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", stdClass=" + stdClass + ", scholership=" + scholership + ", totalAmount=" + totalAmount + ", paidAmount=" + paidAmount + ", due=" + due + '}';
    }

}
