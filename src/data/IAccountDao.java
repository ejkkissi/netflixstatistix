package data;

import model.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> getAllAccounts();
    Account getAccountByEmail(String email);
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
}
