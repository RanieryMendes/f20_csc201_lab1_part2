/*
Raniery Mendes
CSC201 Fall 2020
Programming Assignment 1 - Part 2
September 15, 2020
 */

//Class of Tellers that will store all data regarding the tellers
//This class will be used to create the Teller objects that will be implemented in a listArray in main

public class Tellers {

    private int waitTime;

    Tellers(){
        this.waitTime=0;
    }

    public void setWaitTime(int time){
        this.waitTime= time;
    }

    public int getWaitTime() {
        return waitTime;
    }


    //this function reduces the value stored in waitTime with the purpose of representing that a minute has passed
    //that is, it illustrates that a teller has spent a minute with a customer
    public void reduceTime() {
        if (this.waitTime == 0) {
            //do nothing. Teller is free then.
        }
        else {
            this.waitTime--;
        }
    }
}
