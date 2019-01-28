package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount)  {
        balance -= amount;
    }

    /**
     * purpose: checks for invalid emails based on existence of '@' symbol and if a '.' occurs after that '@'
     * @param email is a string
     * @return boolean false if '@' isn't present or '.' does not come after an '@'
     */
    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1 || email.lastIndexOf('.') < email.indexOf('@')){ //this still wouldn't work well, since email 'a.@y.' would pass
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * purpose: check if amount is limited to 2 decimal places and that it is a positive number
     * @param amount
     * @return true if purpose conditions met, otherwise false
     */
    public static boolean isAmountValid(double amount){
            return false;
    }
}
