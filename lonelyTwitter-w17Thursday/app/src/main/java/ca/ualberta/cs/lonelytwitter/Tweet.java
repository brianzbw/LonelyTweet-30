package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shida3 on 1/19/17.
 *
 * tweet class is a super class that implements tweet table
 */

public abstract class Tweet implements Tweetable{
    private Date date;
    private String message;

    /**
     *
     * @param message
     */
    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }

    /**
     *
     * @param date
     * @param message
     */
    public Tweet(Date date, String message){
        this.message = message;
        this.date = date;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    /**
     * check if the message is too long
     * @param message
     * @throws TweetTooLongException
     */
    public void setMessage(String message) throws TweetTooLongException{
        if (message.length() > 140){
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    public abstract Boolean isImportant();

    @Override
    public String toString(){
        return date.toString() + " | " + message;
    }
}
