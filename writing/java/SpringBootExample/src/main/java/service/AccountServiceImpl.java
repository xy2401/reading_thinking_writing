package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import bean.Account;

//the implementation doing nothing at the moment

@Service
public class AccountServiceImpl implements AccountService {

 public void insertAccount(Account acc) {
     // do something...
 }

 public List<Account> getAccounts(String name) {
	 List<Account> accounts = new ArrayList<>();
	 return accounts ;
     // do something...
 }

}