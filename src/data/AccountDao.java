package data;

import managers.DBManager;
import model.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AccountDao implements IAccountDao {
    @Override
    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        DBManager.getInstance().query("SELECT * FROM account", (rs) -> {
            try {
                    while (rs.next()) {
                        Account a = new Account(rs.getString("email"),
                                                rs.getString("name"),
                                                rs.getString("address"),
                                                rs.getString("city"));
                        list.add(a);
                    }
                } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public Account getAccountByEmail(String email) {
        Account account = new Account();
        DBManager.getInstance().query("SELECT * FROM account WHERE email = " + email, (rs) -> {
            try {
                while (rs.next()) {
                    account.setEmail(rs.getString("email"));
                    account.setName(rs.getString("name"));
                    account.setAddress(rs.getString("address"));
                    account.setCity(rs.getString("city"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return account;
    }

    @Override
    public void createAccount(Account account) {
        DBManager.getInstance().query("INSERT INTO account (email, name, address, city) VALUES"
                                        + "(" + account.getEmail() + account.getName() + account.getAddress() + account.getCity() + ")", null);
    }

    @Override
    public void updateAccount(Account account) {
        DBManager.getInstance().query("UPDATE account\n"
                                        + "SET email = " + account.getEmail()
                                        +",name = " + account.getName()
                                        +",address = " + account.getAddress()
                                        +",city = " + account.getCity() + "\n"
                                        + "WHERE email = " + account.getEmail(), null);
    }

    @Override
    public void deleteAccount(Account account) {
        DBManager.getInstance().query("DELETE FROM account WHERE email = " + account.getEmail(), null);
    }
}
