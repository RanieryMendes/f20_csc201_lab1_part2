/*
Raniery Mendes
CSC201 Fall 2020
Programming Assignment 1 - Part 2
September 15, 2020
 */

//This program performs a bank simulation in which people stand in line waiting to be assisted by a Teller
//This line is implemented using a Queue through a template class.

//The inputs are inserted through the command line


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        //Instance of the queue class that will be the queue of customers
        Queue queue = new Queue();


        //constant that sets the maximum period of simulation
        final int MAXCLOCK = 120;

        //probability of arrival during an interval. Read value from command line
        double p = Double.parseDouble(args[0]);

        //# of tellers
        int numTellers = Integer.parseInt(args[1]);

        // the maximum amount of time - in minutes - any costumer will need for services
        int maxService = Integer.parseInt(args[2]);

        //average length of the queue
        double averageQLength = 0;

        //the max length of the queue and at what time it happened
        int maxQLenght = 0;

        int timeOfMaxQLength = 0;

        //Max time someone had to wait on line
        int maxWaitTime = 0;

        //Average time someone had to wait on line
        double averageWaitTime = 0;


        int totalWaitTime = 0;

        int peopleServed = 0;

        int queueLength=0;

        int maxLength = 0;


        //displays to the user the info inserted into the command line
        System.out.println("Information about the Simulation");
        System.out.println("");
        System.out.print("Probability: " + p + " ");
        System.out.print("Number of Tellers: " + numTellers + " ");
        System.out.println("Maximum time of service with teller: " + maxService + " ");
        System.out.println(" ");

        //variable that will track the time as well as people are in line
        int trackTime = 0;



        //variable that store the amount of time the customer will spend with the teller
        int timeCostumerNeedsToWait;


        //TELLERS
        //Dynamically allocate an array of Tellers
        ArrayList<Tellers> listOfTellers = new ArrayList<Tellers>(numTellers);
        for(int i=0; i<numTellers; i++ ){

            //create a Teller object and insert it into the ArrayList of Tellers

            Tellers name = new Tellers();
            listOfTellers.add(name);
        }





        //loop that will represent the line. It will iterate until trackTime reaches MAXCLOCK
        while(trackTime < MAXCLOCK){

            //add to variable queueLength the current size of the queue
            //the summation of all the lengths that existed for each iteration of the loop will used to calculate the
            //average queue length
            queueLength += queue.size();


            if(queue.size() > maxLength){
                maxQLenght = queue.size();

                //store at which time the max Q length occured

                timeOfMaxQLength = trackTime;

            }


            double randomV = Math.random()*1;


            //probability that checks whether a new customer arrived to the bank(line) or not
            if(randomV <= p){

                //create a new instance of the class Customer
                Customer customer = new Customer();
                customer.setArrivalTime(trackTime);

                System.out.print("Customer arriving to queue. ");

                //get a random number that will be the total amount of time the customer will be with the teller
                timeCostumerNeedsToWait =(int) (Math.random()* maxService) + 1;

                customer.setTime_to_wait(timeCostumerNeedsToWait);

                System.out.println("This customer will require " + timeCostumerNeedsToWait + " for service.");


                //boolean that will be later assessed to
                //determine if the new customer will go directly to a teller or added to the queue to wait
                boolean goToQueue = true;

                //check if there are already customers in the queue
                if(queue.isEmpty()) {

                    for (int i = 0; i < numTellers; i++) {


                        //if wait time is Zero then teller is free to receive a new customer
                        if (listOfTellers.get(i).getWaitTime() == 0) {

                            //inform which teller is free and displays that customer is going to that specific teller
                            System.out.println("Teller " + i + " is free!");
                            System.out.print("Costumer going to teller " + i + " ");


                            //store the total wait time of all customers
                            totalWaitTime= totalWaitTime + customer.timeSpentInLine(trackTime);
                            //System.out.println("total time: " + totalWaitTime);
                            System.out.println("This customer had to wait " + customer.timeSpentInLine(trackTime) + " minute(s) in line");


                            //keep track of the longest wait time
                            if(maxWaitTime < customer.timeSpentInLine(trackTime)) {
                               maxWaitTime= customer.timeSpentInLine(trackTime);
                            }

                            listOfTellers.get(i).setWaitTime(customer.timeWithTeller());

                            //if Teller is free assign costumer to that teller

                            peopleServed= peopleServed +1 ;


                            goToQueue = false; //evaluates to false because customer was already allocated to a teller
                            break; // so for loop will break since there is no need to look for another free teller

                        }




                    }


                    if (goToQueue) {
                        //add customer to the queue because there is no teller available
                        System.out.println("Customer Enqueued");
                        queue.enqueue(customer);
                    }
                }

                //if the queue is not empty this else statement, if there is a teller available,
                // will allocate a customer from the queue into that teller
                else {


                    //Iterate through the list of tellers to know if there is any free teller.
                    //If yes, a customer from the queue will be allocated to receive that assistance

                    for (int i = 0; i < numTellers; i++) {

                        //if wait time is Zero then teller is free to receive a new customer
                        if (listOfTellers.get(i).getWaitTime() == 0) {


                            System.out.println("Teller " + i + " is free!");
                            System.out.println("Costumer going to teller " + i + " ");

                            Customer a = new Customer();
                            a = (Customer) queue.pop();
                            peopleServed= peopleServed +1 ;

                            queue.dequeue();

                            System.out.println("Costumer had to wait " + a.timeSpentInLine(trackTime) + " minutes in line");
                            if(maxWaitTime < a.timeSpentInLine(trackTime)) {
                                maxWaitTime= a.timeSpentInLine(trackTime);
                            }

                            totalWaitTime= totalWaitTime + a.timeSpentInLine(trackTime);
                           // System.out.println("total time: " + totalWaitTime);
                           // System.out.println(totalWaitTime);
                            listOfTellers.get(i).setWaitTime(a.timeWithTeller());

                            //if Teller is free assign costumer to that teller


                            goToQueue = false; //evaluates to false because customer was already allocated to a teller
                            break; // so for loop will break

                        }


                        //statement that shows the tellers' current situation
                        //System.out.println(listOfTellers.get(i).getWaitTime());


                    }


                    if (goToQueue) {

                        //add customer to the queue because there is no  teller available
                        queue.enqueue(customer);
                    }
                }


            }

            //loop to iterate through the list of tellers to reduce by one minute the time allocated in each teller.
            //This reduction represents a minute the teller spent with a costumer
            for (int i = 0; i < listOfTellers.size(); i++){
                listOfTellers.get(i).reduceTime();
            }




            //update clock
            trackTime++;


        }// finish simulation in while loop and print info to the user

        averageWaitTime = totalWaitTime/ peopleServed;

        averageQLength = queueLength/ MAXCLOCK;

        //print out statistics of the queue
        System.out.println("Average Queue: " + averageQLength + " people in line.");
        System.out.println("Max Queue: " + maxQLenght + " at time " + timeOfMaxQLength) ;
        System.out.println("Max Wait: " +maxWaitTime +  " minutes" );
        System.out.println("Average wait time: " + averageWaitTime);


    }
}
