package admin;

public class AccountsOfficer {

    private int accountantId;
    private String accountantName;
    private String accountantPassword;

    public AccountsOfficer() {
    }

    public AccountsOfficer(int accountantId, String accountantName, String accountantPassword) {
        this.accountantId = accountantId;
        this.accountantName = accountantName;
        this.accountantPassword = accountantPassword;
    }

    public int getAccountantId() {
        return accountantId;
    }

    public void setAccountantId(int accountantId) {
        this.accountantId = accountantId;
    }

    public String getAccountantName() {
        return accountantName;
    }

    public void setAccountantName(String accountantName) {
        this.accountantName = accountantName;
    }

    public String getAccountantPassword() {
        return accountantPassword;
    }

    public void setAccountantPassword(String accountantPassword) {
        this.accountantPassword = accountantPassword;
    }

    @Override
    public String toString() {
        return "AccountsOfficer{" + "accountantId=" + accountantId + ", accountantName=" + accountantName + ", accountantPassword=" + accountantPassword + '}';
    }

}
