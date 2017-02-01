package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This is class is the main view class of the project. <br> in this class, user interaction
 * and file manipulatiion is performed.
 * all files are in the form of "json" files taht are stored in Emulator's access
 * <pre>
 *      pre-fromatted text: <br>
 *          File explorer -> data -> datat -> ca.ualberta.cs.lonelytwitter -> files -> file/sav
 *
 * </pre>
 * <code>begin <br>
 *    end. </code>
 * the file name is indeicated in the &nbsp &nbsp &nbsp FILENAME constant.
 * </ul>
 * <ol>
 *     <li>item 1</li>
 *     <li>item 2</li>
 *     <li>item 3</li>
 *
 * </ol>
 *
 * @author bz2
 * @version 1.0
 * @see Tweet
 * @since 0.5
 */
public class LonelyTwitterActivity extends Activity {
	/**
	 * THE FILE	 THAT all the tweet are saved there. the formaty of the file is JSON.
	 * @see #loadFromFile()
	 * @see #
	 */
	private static final String FILENAME = "file.sav";
	private enum TweetListOrdering {DATE_ASCENDING, DATE_DESCENDING, TEXT_ASCENDING, TEXT_DESCENDING}
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweetList;
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				text = trimExtraSpaces(text);

				Tweet tweet = new NormalTweet(text);

				tweetList.add(tweet);

				adapter.notifyDataSetChanged();

				saveInFile();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		loadFromFile();

		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * Trims extra spaces using regular expression.
	 *
	 * @param inputString string that needs to be cleared of extra spaces
	 * @return resulting sting
     */
	private String trimExtraSpaces(String inputString){
		inputString = inputString.replaceAll("\\s+"," ");
		return inputString;
	}

	/**
	 * This method sorts itens in the tweet list and adapter.
	 * @param ordering ordering to be used
     */
	private void sortTweetListItem(TweetListOrdering ordering){


	}
	/**
	 * Loads file from specified file.
	 *
	 * @throws TweetTooLongException if the text is too long.
	 * @exception FileNotFoundException if the file is not created first
	 *
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			Gson gson = new Gson();
			// Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
			// 2017-01-26 17:53:59
			tweetList = gson.fromJson(in, new TypeToken<ArrayList<NormalTweet>>(){}.getType());

			fis.close();
		} catch (FileNotFoundException e) {
			tweetList = new ArrayList<Tweet>();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * saves tweets to a specified file in JSON format,
	 * @throws FileNotFoundException if file folder doesnt exist.
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

			Gson gson = new Gson();

			gson.toJson(tweetList, out);

			out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}