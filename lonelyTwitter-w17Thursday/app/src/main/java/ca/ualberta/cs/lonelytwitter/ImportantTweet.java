package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * this is the class for important tweets
 * Created by shida3 on 1/19/17.
 */


public class ImportantTweet extends Tweet {

    /**
     * @param message
     */
    public ImportantTweet(String message) {
        super(message);
    }

    /**
     *
     * @param date
     * @param message
     */
    public ImportantTweet(Date date, String message) {
        super(date, message);
    }

    @Override

    public Boolean isImportant(){
        return true;
    }
}
