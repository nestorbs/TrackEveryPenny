package com.nestorbs;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.TextView;

import com.nestorbs.trackeverypennyandroid.BrowseTransactionsActivity;
import com.nestorbs.trackeverypennyandroid.R;

@RunWith(RobolectricTestRunner.class)
public class DisplayNumberOfTransactionsTest {
	
  @Test
  public void happyPath() throws Exception {
	  BrowseTransactionsActivity browseTransactionsActivity = Robolectric.buildActivity(BrowseTransactionsActivity.class).create().get(); 
	
	  browseTransactionsActivity.displayNumberOfTransactions(13);
	  final TextView transactionsCountView
	    		= (TextView) browseTransactionsActivity.findViewById(R.id.transactionsCount);
	
	  assertEquals("13", transactionsCountView.getText().toString());
  }
  
  @Test
  public void rejectNegativeNumber() throws Exception {
	  BrowseTransactionsActivity browseTransactionsActivity = 
			  Robolectric.buildActivity(BrowseTransactionsActivity.class).create().get();
	  try{
		  browseTransactionsActivity.displayNumberOfTransactions(-1);
		  fail("Why did you display a negative number of transations? " + "That's crazy talk!");
	  } catch (ProgrammerMistake success) {
		  assertTrue(success.getCause() instanceof IllegalArgumentException);
	  }
  }
}