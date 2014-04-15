package com.nestorbs.trackeverypennyandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nestorbs.BrowseTransactionsModel;
import com.nestorbs.BrowseTransactionsPresenter;
import com.nestorbs.BrowseTransactionsView;
import com.nestorbs.RendersView;
import com.nestorbs.toolkit.ProgrammerMistake;

public class BrowseTransactionsActivity extends Activity implements BrowseTransactionsView{
	private final RendersView rendersView;
	
	public BrowseTransactionsActivity() {
	    // We can't chain the constructor, because the instance in the process of being created is itself the view.
	    // We have to wait for super() to be (implicitly) invoked.
	    this.rendersView = new BrowseTransactionsPresenter(
	        new BrowseTransactionsModel() {
	          @Override
	          public int countTransactions() {
	            return 12;
	          }
	        }, this);
	  }

	  public BrowseTransactionsActivity(
	      RendersView rendersView
	  ) {
	    this.rendersView = rendersView;
	  }

	  @Override
	  protected void onResume() {
	    super.onResume();
	    // Arbitrarily, I assume that I should do my work after the superclass, but I don't really know.
	    rendersView.render();
	  }
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_transactions);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse_transactions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_browse_transactions, container, false);
			return rootView;
		}
	}
	
	public void displayNumberOfTransactions (int transactionCount){
		if(transactionCount < 0)
			throw new ProgrammerMistake( new IllegalArgumentException(
					String.format("number of transactions can't be negative, but it's %1$d", transactionCount)));
		final TextView transactionsCountView = (TextView) findViewById(R.id.transactionsCount);
		transactionsCountView.setText(String.format("%1$d", transactionCount));
	}

	public void exportAllTransactionsButton(View clicked) {
		//Nothing yet
	}
}
