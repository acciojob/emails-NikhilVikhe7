package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Gmail extends Email {

//    MailList mails;

    private static class  Mail{
        private final String message;
        private final Date date;
        private String sender;

        public Mail(Date date, String sender, String message) {
            this.message = message;
            this.date = date;
            this.sender = sender;
        }
    }

    private final List<Mail> inbox;
    private final List<Mail> trash;

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inbox = new LinkedList<>();
        this.trash = new LinkedList<>();
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
//        mails.addMail(date, sender, message);


        if(inbox.size() < this.inboxCapacity){
            inbox.add(new Mail(date, sender, message));
            return;
        }
        trash.add(inbox.remove(0));
        inbox.add(new Mail(date,sender, message));



    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
//        mails.deleteMail(message);

        for(int i = 0; i < inbox.size(); i++){
            if(inbox.get(i).message.equals(message)){
                trash.add(inbox.remove(i));
                return;
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()) return null;
        return inbox.get(inbox.size()-1).message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inboxa
        if(inbox.isEmpty()) return null;
        return inbox.get(0).message;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int cnt = 0;
        for(Mail mail : inbox){
            boolean equalOrAfterStart = mail.date.after(start) || mail.date.equals(start);
            boolean equalOrBeforeEnd = mail.date.before(end) || mail.date.equals(end);
            if(equalOrAfterStart && equalOrBeforeEnd) cnt++;
        }

        return cnt;

    }

    public int getInboxSize(){
        // Return number of mails in inbox

        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}

//class MailList {
//
//    class Mail{
//        private final String message;
//        private final Date date;
//        private String sender;
//
//        public Mail(Date date, String sender, String message) {
//            this.message = message;
//            this.date = date;
//            this.sender = sender;
//        }
//    }
//
//    private final List<Mail> inbox;
//    private final List<Mail> trash;
//
//    private final int inboxCapacity;
//
//    public MailList(int inboxCapacity) {
//        this.inbox = new LinkedList<>();
//        this.trash = new LinkedList<>();
//        this.inboxCapacity = inboxCapacity;
//    }
//
//    public void addMail(Date date, String sender, String message){
//
//        if(inbox.size() < this.inboxCapacity){
//            inbox.add(new Mail(date, sender, message));
//            return;
//        }
//        trash.add(inbox.remove(0));
//        inbox.add(new Mail(date,sender, message));
//    }
//
//
//    public void deleteMail(String message){
//        for(int i = 0; i < inbox.size(); i++){
//            if(inbox.get(i).message.equals(message)){
//                trash.add(inbox.remove(i));
//                return;
//            }
//        }
//    }
//
//    public String getLatestMessage() {
//        return inbox.get(inbox.size()-1).message;
//    }
//
//    public String getOldestMessage() {
//        return inbox.get(0).message;
//    }
//
//    public int findMailsBetweenDates(Date start, Date end) {
//        int cnt = 0;
//        for(Mail mail : inbox){
//            boolean equalOrAfterStart = mail.date.after(start) || mail.date.equals(start);
//            boolean equalOrBeforeEnd = mail.date.before(end) || mail.date.equals(end);
//            if(equalOrAfterStart && equalOrBeforeEnd) cnt++;
//        }
//
//        return cnt;
//    }
//
//    public int inboxSize() {
//        return inbox.size();
//    }
//
//    public int trashSize() {
//        return trash.size();
//    }
//
//    public void emptyTrash() {
//        trash.clear();
//    }
//}