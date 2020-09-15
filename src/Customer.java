/*
Raniery Mendes
CSC201 Fall 2020
Programming Assignment 1 - Part 2
September 15, 2020
 */

//Class of Customer that will store all data regarding the customers
//This class will be used to create the customer objects that will be implemented in main using my own-made Queue class

public class Customer {
    private int time_to_wait; //variable to store the amount of time the person will have to spend with the teller
    private int arrivalTime; //variable to keep track of when the person got to the bank
    private int timeSpent; //variable to store for how long person had to wait in line


    Customer(){
        this.time_to_wait=0;
        this.arrivalTime=0;
        this.timeSpent=0;
    }

    //store the specific moment the customer arrive to the bank (line)
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    //store how long the person will spend with the Teller
    public void setTime_to_wait(int time_to_wait) {
        this.time_to_wait = time_to_wait;
    }

    //return how long the customer spent waiting to be assigned to a teller
    public int timeSpentInLine(int currTime){
        this.timeSpent = currTime - this.arrivalTime;

       return this.timeSpent;
    }

    //return amount of time person will have to spend with the teller.
    // This function will return the value that will be stored into the array of tellers
    public int timeWithTeller(){

        return this.time_to_wait;
    }
}


