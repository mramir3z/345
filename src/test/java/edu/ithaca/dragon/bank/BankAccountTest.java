package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    //The hard-coded parameters weren't sitting very well with me, so I gave them variables - henceforce the 'Tvals'
        //I'm wondering if I shouldn't have done that, since the values are being edited all across the class. I added a reset() function brought out by that concern. TDD is a wild, *wild* west for me.
    double testBAmount = 200;
    double testWAmount = 100.56;
    double testDouble = 50.60;
    String testEmail = "a@.y.";

    //there's an oversight here. it just occurred to me; every time i want to add a new value to use with these tests
    // I have to add it here too, not just within the class's members.
    void resetTvals(){
        testBAmount = 200;
        testWAmount = 100.56;
        testDouble = 50.60;
        testEmail = "a@.y.";
    }

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

        if (BankAccount.isAmountValid(testWAmount)){
            bankAccount.withdraw(testWAmount);
            assertEquals((testBAmount - testWAmount), bankAccount.getBalance());
            overdrawnTest();
        } else {
            //resetTvals();
            fail("Amount not valid!");

        }
    }

    /**
     * purpose: checks for negative value in account balance
     */
    @Test
    void overdrawnTest(){
        BankAccount bankAccount = new BankAccount(testEmail, testBAmount);
        bankAccount.withdraw(testWAmount);
        if (bankAccount.getBalance() < 0){
            fail("Overdrawn!");
        }
        resetTvals();
    }

    //a 'pure' method; no side effects.
    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid(testEmail));
        assertFalse(BankAccount.isEmailValid(""));
        resetTvals();
    }

    //?
    @Test
    void isAmountValid(){
        assertTrue(BankAccount.isAmountValid(testDouble));
        resetTvals();
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount(testEmail, testBAmount);

        assertEquals(testEmail, bankAccount.getEmail());
        assertTrue(bankAccount.isAmountValid(testBAmount));
        assertEquals(testBAmount, bankAccount.getBalance());

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }
}