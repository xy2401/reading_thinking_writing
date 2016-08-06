package service;

import java.util.List;

 
import bean.Account;

 
public interface  AccountService {

    public void insertAccount(Account account);

    public List<Account> getAccounts(String name);
}
