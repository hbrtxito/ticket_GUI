/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class FileControllers {

    public FileControllers(){
    
    }
    
    public boolean addNewTicket(Ticket ticket) {
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/text.txt", true)))) {
            out.println(ticket.getTicketID()+"|"+ticket.getDescription()+"|"+ticket.getPriority()+"|"+ ticket.getReporter()+ "|"+ ticket.getDateReported());
            out.close();
            return true;
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            return false;
        }
        
    }
    
    public List<String> getAllTicket() {
         
        List<String> allTickets = new ArrayList<>();
        
        File text = new File("src/text.txt");
      
        //Creating Scanner instnace to read File in Java
        Scanner scnr;
        try {
            scnr = new Scanner(text);
        
            //Reading each line of file using Scanner class
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();

                allTickets.add(line);
            } 
            scnr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allTickets;
    }
    
    public boolean updateTicket(String selected) {
        
        File text = new File("src/text.txt");
      
        //Creating Scanner instnace to read File in Java
        Scanner scnr;
        try {
            scnr = new Scanner(text);
        
            String[] selectedParts = selected.split("|");
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/text2.txt", false)));
                 
            
            //Reading each line of file using Scanner class
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();

                String[] itemParts = line.split("|");
                
                if(selectedParts[0].compareToIgnoreCase(itemParts[0])==0){
                    line = selected;
                }
                
                  
                out.println(line);
              
                    
                  
                
            }
            out.close();
            scnr.close();
            
            File newFile = new File("src/text2.txt");
            
            File removeFile = new File("src/text.txt");
            
            newFile.renameTo(removeFile);
            
            return true;
        } catch (Exception ex) {
            return false;
        }
        
    }
    
}
