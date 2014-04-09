package com.nestorbs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.TextView;

import com.nestorbs.trackeverypennyandroid.BrowseTransactionsActivity;
import com.nestorbs.trackeverypennyandroid.R;

@RunWith(RobolectricTestRunner.class)
public class DisplayNumberOfTransactionsTest extends BrowseTransactionsViewContract {
	
  @Test
  public void happyPath() throws Exception {
	  BrowseTransactionsActivity browseTransactionsActivity = Robolectric.buildActivity(BrowseTransactionsActivity.class).create().get(); 
	
	  browseTransactionsActivity.displayNumberOfTransactions(13);
	  final TextView transactionsCountView
	    		= (TextView) browseTransactionsActivity.findViewById(R.id.transactionsCount);
	
	  assertEquals("13", transactionsCountView.getText().toString());
  }
 
  @Override
	protected BrowseTransactionsView initializeView() {
		final BrowseTransactionsActivity browseTransactionsActivity = 
				Robolectric.buildActivity(BrowseTransactionsActivity.class).create().get();
		return browseTransactionsActivity;
	}
}