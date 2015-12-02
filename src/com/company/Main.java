package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Main {

    static JFrame window = new JFrame();
    static JTextArea problemTextArea;
    static JTextArea clientTextArea;
    static JTextArea resolutionTextArea;
    static JComboBox comboPriority;
    static JComboBox comboAllTickets;
    static JLabel message;
    static FileControllers fileControllers = new FileControllers();
    static JButton resolutionButton;
    
    
    
    static int screenSize = 500;

    public enum Priority {

        HIGH(1), MEDIUM(2), LOW(3);
        private int value;

        private Priority(int value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
   // write your code here

        JPanel content = new JPanel();
        content.setLayout(null);
        //content.add(gamePanel, BorderLayout.CENTER);

     

        problemTextArea = new JTextArea("Enter problem");
        problemTextArea.setBounds(20, 40, 200, 40);
        content.add(problemTextArea);//Who reported this issue?

        clientTextArea = new JTextArea("Who reported this issue?");
        clientTextArea.setBounds(20, 90, 200, 40);
        content.add(clientTextArea);//
        
        comboPriority = new JComboBox(Priority.values());//enviarle lista
        comboPriority.setPreferredSize(new Dimension(285, 20));
        comboPriority.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboPriority.setBounds(20, 150, 200, 20);
        content.add(comboPriority);
        
        
        comboAllTickets = new JComboBox(fileControllers.getAllTicket().toArray());//enviarle lista
        comboAllTickets.setPreferredSize(new Dimension(285, 20));
        comboAllTickets.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 13));
        comboAllTickets.setBounds(20, 450, 400, 20);
        
        comboAllTickets.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
                String selected = comboAllTickets.getSelectedItem().toString();
                
                System.out.println(selected);
                
                resolutionTextArea.setVisible(true);
                resolutionButton.setVisible(true);
                
                String[] parts = selected.split("|");
                
                
            }
        });
        
        content.add(comboAllTickets);
        
        resolutionTextArea = new JTextArea("Enter Resolution");
        resolutionTextArea.setBounds(250, 40, 200, 40);
        resolutionTextArea.setVisible(false);
        content.add(resolutionTextArea);
        
        message = new JLabel("", JLabel.LEFT);
        message.setBounds(100, 0, 300, 40);
        content.add(message);
        
        JButton OKButton = new JButton("Enter Ticket");
        //OKButton.addActionListener(new MyAction());
        OKButton.setBounds(20, 200, 200, 40);
        content.add(OKButton, BorderLayout.CENTER);
        OKButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("Read file");

                Ticket ticket = new Ticket(problemTextArea.getText(), comboPriority.getSelectedIndex(), clientTextArea.getText(), new Date());

                if(fileControllers.addNewTicket(ticket)){
                    
                    Random rand = new Random();

                    float r = rand.nextFloat();
                    float g = rand.nextFloat();
                    float b = rand.nextFloat();

                    Color randomColor = new Color(r, g, b);
                    
                    message.setForeground(randomColor);
                    
                    message.setText("A new ticket was successfully added");
                    
//                    comboAllTickets.removeAllItems();
//                    
//                    comboAllTickets.add(fileControllers.getAllTicket());
                }
                
            }
        });
        
        
        resolutionButton = new JButton("Update resolution");
        //OKButton.addActionListener(new MyAction());
        resolutionButton.setBounds(250, 200, 200, 40);
        resolutionButton.setVisible(false);
        content.add(OKButton, BorderLayout.CENTER);
        OKButton.addActionListener(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("Read file");

                String selected = comboAllTickets.getSelectedItem().toString();
                
                System.out.println(selected);
                
                String[] itemParts = selected.split("|");
                List<String> items = new ArrayList<>();
                items.addAll(items);
                Iterator it = items.iterator();
                
                while (it.hasNext()) {
                    String next = (String)it.next();
                    if(it.hasNext())
                        selected = selected + next + "|";
                    else
                        selected = selected + next;
                }
                
                selected = selected + "|" + resolutionTextArea.getText() + "|" + new Date();
                
                if(fileControllers.updateTicket(selected)){
                    Random rand = new Random();

                    float r = rand.nextFloat();
                    float g = rand.nextFloat();
                    float b = rand.nextFloat();

                    Color randomColor = new Color(r, g, b);
                    
                    message.setForeground(randomColor);
                    
                    message.setText("Resolution was successfully added");
                }
                
            }
        });
        content.add(resolutionButton);
        
        

        //window.setUndecorated(true);   //Hides the title bar.

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        window.setContentPane(content);
        window.setSize(screenSize, screenSize);
        window.setLocation(100, 100);    //Where on the screen will this window appear?
        window.setVisible(true);

        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();

        LinkedList<Ticket> SearchListTickets = new LinkedList<>(); // list of the tickets that were searched

        LinkedList<ResolvedTicket> resolvedTickets = new LinkedList<>(); //

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("1. Enter Ticket\n2. Delete by ID\n3. Delete by Issue\n4.Search by Name\n5. Display All Tickets\n4. Quit");
            int task = Integer.parseInt(scan.nextLine());

            if (task == 1) {
                //Call addTickets, which will let us enter any number of new tickets
                addTickets(ticketQueue);

            } else if (task == 2) {
                //delete a ticket by ID
                deleteTicket(ticketQueue, resolvedTickets);

            } else if (task == 3) {
                        //delete by Issue
                //deleteTicket();
                deleteTicket_Issue(ticketQueue, SearchListTickets, resolvedTickets);
            } else if (task == 4) {
                //Search by name
                SearchByName(ticketQueue, SearchListTickets);

            } else if (task == 5) {
                //Quit. Future prototype may want to save all tickets to a file
                System.out.println("Quitting program");
                break;
            } else {
                        //this will happen for 3 or any other selection that is a valid int
                //TODO Program crashes if you enter anything else - please fix
                //Default will be print all tickets
                printAllTickets(ticketQueue);

            }
        }

        scan.close();

    }

    public static int isValid_deleteID(String delete) {
        int deleteID = 0;
        try {
            deleteID = Integer.parseInt(delete);

        } catch (NumberFormatException nfe) {
            System.out.println("The format of your ID is incorrect!");
        }

        return deleteID;
    }

    public static Date isValid_Date(String date) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date newDate = null;
        try {
            newDate = df.parse(date);

        } catch (ParseException psex) {
            System.out.println("The format of the date is incorrect!");
        }

        return newDate;
    }

    //Move the adding ticket code to a method
    protected static void addTickets(LinkedList<Ticket> ticketQueue) {
        Scanner sc = new Scanner(System.in);

        boolean moreProblems = true;
        String description;
        String reporter;
        //let's assume all tickets are created today, for testing. We can change this later if needed
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        int priority;

        while (moreProblems) {
            System.out.println("Enter problem");
            description = sc.nextLine();
            System.out.println("Who reported this issue?");
            reporter = sc.nextLine();
            System.out.println("Enter priority of " + description);

            priority = Integer.parseInt(sc.nextLine());

            Ticket t = new Ticket(description, priority, reporter, dateReported);
            //ticketQueue.add(t);

            addTicketInPriorityOrder(ticketQueue, t);
            //To test, let's print out all of the currently stored tickets
            printAllTickets(ticketQueue);

            System.out.println("More tickets to add?");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                moreProblems = false;
            }
        }

    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All open tickets ----------");

        for (Ticket t : tickets) {
            System.out.println(t); //Write a toString method in Ticket class
            //println will try to call toString on its argument
        }
        System.out.println(" ------- End of ticket list ----------");

    }

    protected static void deleteTicket_Issue(LinkedList<Ticket> ticketQueue, LinkedList<Ticket> SearchListTicket, LinkedList<ResolvedTicket> resolvedTickets) {
        Scanner getanswer = new Scanner(System.in);
        String answer;
                //printAllTickets(ticketQueue);//display all tickets
        //Calling the method to search by name

        SearchByName(ticketQueue, SearchListTicket);

        System.out.println("Do you want to delete a ticket by its ID ?");
        answer = getanswer.nextLine();
        answer = answer.toUpperCase();

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");
            return;
        } else if (answer.equals("Y")) {
            deleteTicket(SearchListTicket, resolvedTickets);
        } else {
            Scanner deleteScanner = new Scanner(System.in);
            System.out.println("Enter Issue of ticket to delete");
            /**
             * * checking**
             */
            String delete_issue = deleteScanner.nextLine();

            //Loop over all tickets. Delete the one with this ticket ID
            boolean found = false;
            for (Ticket ticket : ticketQueue) {
                String issue_desc = ticket.getDescription();

                if (issue_desc.contains(delete_issue)) {
                    found = true;
                    ticketQueue.remove(ticket);
                    System.out.println("Ticket with issue" + delete_issue + "was remove");
                    break; //don't need loop any more.
                }
            }
            if (found == false) {
                System.out.println("Ticket ID not found, no ticket deleted");
                System.out.println(" Please enter the ID ticket again");
                deleteTicket_Issue(ticketQueue, SearchListTicket, resolvedTickets);

                //TODO – re-write this method to ask for ID again if not found
            }
            printAllTickets(ticketQueue);  //print updated list

        }

    }

    protected static void deleteTicket(LinkedList<Ticket> ticketQueue, LinkedList<ResolvedTicket> resolvedTickets) {
        printAllTickets(ticketQueue);   //display list for user

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");
            return;
        }

        Scanner deleteScanner = new Scanner(System.in);
        System.out.println("Enter ID of ticket to resolve");
        /**
         * * checking**
         */
        String delete = deleteScanner.nextLine();

        int deleteID = isValid_deleteID(delete);

        //Loop over all tickets. Delete the one with this ticket ID
        boolean found = false;
        for (Ticket ticket : ticketQueue) {
            if (ticket.getTicketID() == deleteID) {
                found = true;
                System.out.println("Enter the resolution for this ticket");
                String resolution = deleteScanner.nextLine();

                System.out.println("Enter the resolution DATE for this ticket");
                String resolutionString = deleteScanner.nextLine();
                Date resolutionDate = isValid_Date(resolutionString);

                ResolvedTicket resolved = new ResolvedTicket(ticket.getDescription(), ticket.getPriority(),
                        ticket.getReporter(), ticket.getDateReported(), resolutionDate, resolution);

                resolvedTickets.add(resolved);
                System.out.println(resolved);
                ticketQueue.remove(ticket);
                System.out.println(String.format("Ticket %d deleted", deleteID));
                break; //don't need loop any more.
            }
        }
        if (found == false) {
            System.out.println("Ticket ID not found, no ticket deleted");
            System.out.println(" Please enter the ID ticket again");
            deleteTicket(ticketQueue, resolvedTickets);

            //TODO – re-write this method to ask for ID again if not found
        }
        printAllTickets(ticketQueue);  //print updated list

    }

            //Method - Search by name
    protected static void SearchByName(LinkedList<Ticket> ticketQueue, LinkedList<Ticket> SearchListTicket) {
        // printAllTickets(ticketQueue);//display all tickets

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to search!\n");
            return;
        }

        Scanner SearchTicket = new Scanner(System.in);
        System.out.println("Enter the ticket description: ");
        /**
         * * checking**
         */
        String Search = SearchTicket.nextLine();

        //Loop over all tickets. Delete the one with this ticket ID
        boolean found = false;

        System.out.println(" here are your tickets ");
        for (Ticket ticket : ticketQueue) {
            String issue_desc = ticket.getDescription();

            if (issue_desc.contains(Search)) {
                SearchListTicket.add(ticket);
                found = true;
                //TicketQueue.remove(ticket);
                System.out.println(ticket);
                        //System.out.println( );
                //break; //don't need loop any more.
            }
        }
        if (found == false) {
            System.out.println("Ticket not found ");
                    //System.out.println(" Please enter the ID ticket again");
            //deleteTicket_Issue(ticketQueue);

            //TODO – re-write this method to ask for ID again if not found
        }
                // printAllTickets(ticketQueue);  //print updated list

    }

    protected static void addTicketInPriorityOrder(LinkedList<Ticket> tickets, Ticket newTicket) {

        //Logic: assume the list is either empty or sorted
        if (tickets.size() == 0) {//Special case - if list is empty, add ticket and return
            tickets.add(newTicket);
            return;
        }

        //Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
        //Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end
        int newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < tickets.size(); x++) {    //use a regular for loop so we know which element we are looking at

            //if newTicket is higher or equal priority than the this element, add it in front of this one, and return
            if (newTicketPriority >= tickets.get(x).getPriority()) {
                tickets.add(x, newTicket);
                return;
            }
        }

        //Will only get here if the ticket is not added in the loop
        //If that happens, it must be lower priority than all other tickets. So, add to the end.
        tickets.addLast(newTicket);
    }

}
