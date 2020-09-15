/*
Raniery Mendes
CSC201 Fall 2020
Programming Assignment 1 - Part 2
September 15, 2020
 */

import java.util.ArrayList;

//Queue template class that will store the queue of Customers.

public class Queue <T> {

    private ArrayList <T> line;

    Queue(){
        line = new ArrayList<>();
    }


    public void enqueue(T obj){

        line.add(obj);

    }

    public void  dequeue(){

        line.remove(0);

    }

    public boolean isEmpty(){

        return this.line.isEmpty();

    }

    //function that will return the object stored at the front of the queue
    public  T pop(){

        return line.get(0);

    }

    public int size(){
        return this.line.size();
    }



}

