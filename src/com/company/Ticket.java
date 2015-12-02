package com.company;
import java.util.*;

/**
 * Created by hbrtxito on 12/1/2015.
 */
public class Ticket {
    
    public Date getDateReported() {
        return dateReported;
    }

    public String getReporter() {
        return reporter;
    }

    private int priority;
    private String reporter; //Stores person or department who reported issue
    private String description;
    private Date dateReported;

    //STATIC Counter - accessible to all Ticket objects.
    //If any Ticket object modifies this counter, all Ticket objects will have the modified value
    //Make it private - only Ticket objects should have access
    private static int staticTicketIDCounter = 1;
    //The ID for each ticket - instance variable. Each Ticket will have it's own ticketID variable
    protected int ticketID;

    public String getDescription() {
        return description;
    }

    public int getTicketID() {// new
        return ticketID;
    }

    public Ticket(String description, int priority, String reporter, Date date) {
        this.description = description;
        this.priority = priority;
        this.reporter = reporter;
        this.dateReported = date;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }

    protected int getPriority() {
        return priority;
    }


    public  String toString(){
        return("ID= " + this.ticketID + " Issued: " + this.description + " Priority: " +
                this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported);
    }

}