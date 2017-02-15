package ca.ualberta.cs.lonelytwitter;
import android.test.ActivityInstrumentationTestCase2;


/**
 * Created by bz2 on 2/14/17.
 */

public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest(){
        super(LonelyTwitterActivity.class);

    }

    public void  testAddTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test tweet");

        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));


    }

    public void testGetTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Another Test Tweet");

        tweets.add(tweet);
        Tweet returnedTweet = tweets.getTweet(0);

        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
        assertEquals(returnedTweet.getDate(), tweet.getDate());


    }

    public void testDeleTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("last tweet");

        tweets.add(tweet);
        tweets.delete(tweet);

        assertFalse(tweets.hasTweet(tweet));
    }


    public void testStrings(){
        assertEquals("'test' should be 'test'","test", "test");
        assertTrue("'test' should start with 't'", "test".startsWith("t"));
    }

    public void testAdd(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("tests");
        Tweet tweet1 = new NormalTweet("test");
        list.add(tweet);

        assertFalse(list.addTweet(tweet1));
    }

    public void testCout(){
        TweetList list = new TweetList();

        assertEquals(0,list.getCount());
    }
}
