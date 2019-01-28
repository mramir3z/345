package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    //The hard-coded parameters weren't sitting very well with me, so I gave them variables - henceforce the 'Tvals'
        //I'm wondering if I shouldn't have done that, since the values are being edited all across the class. I added a reset() function brought out by that concern. TDD is a wild, *wild* west for me.
    double testBAmount = 200;
    double testWAmount = 100;
    String testEmail = "a@.y.";

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount(testEmail, testBAmount);
        assertEquals(testBAmount, bankAccount.getBalance());
        resetTvals();
    }

    /**
     * purpose: test for validity in correct withdrawal calculation. It doesn't determine if account gets overdrawn, and if it was, by how much
     * note: this is definitely not a pure function.
     */
    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount(testEmail, testBAmount);
        bankAccount.withdraw(testWAmount);
        assertEquals((testBAmount-testWAmount), bankAccount.getBalance());
        overdrawnTest();
    }

    /**
     * purpose: checks for negative value in account balance
     */
    @Test
    void overdrawnTest(){
        BankAccount bankAccount = new BankAccount(testEmail, testWAmount);
        bankAccount.withdraw(testWAmount);
        if (bankAccount.getBalance() < 0){
            fail("Overdrawn!");
        }
        resetTvals();
    }

    //a 'pure' method; no side effects.
    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( testEmail));
        assertFalse(BankAccount.isEmailValid(""));
        resetTvals();
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount(testEmail, 200);

        assertEquals(testEmail, bankAccount.getEmail());
        assertEquals(testBAmount, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    void resetTvals(){
        testBAmount = 200;
        testWAmount = 100;
        testEmail = "a@.y.";
    }
}