package com.nestorbs;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.Button;

import com.nestorbs.trackeverypennyandroid.BrowseTransactionsActivity;
import com.nestorbs.trackeverypennyandroid.R;

//I can't run the Activity without Robolectric, and I
//can't run with both Robolectric and JMock, so I choose
//to run with Robolectric and do the JMock stuff by hand.
@RunWith(RobolectricTestRunner.class)
public class RenderBrowseTransactionsScreenTest {
  private Mockery mockery = new Mockery();

  @Test
  public void zero() throws Exception {
    final BrowseTransactionsModel model
        = mockery.mock(BrowseTransactionsModel.class);
    final BrowseTransactionsView view
        = mockery.mock(BrowseTransactionsView.class);
    final BrowseTransactionsPresenter presenter
        = new BrowseTransactionsPresenter(model, view);

    mockery.checking(
        new Expectations() {{
          allowing(model).countTransactions();
          will(returnValue(0));

          oneOf(view).displayNumberOfTransactions(0);
        }});

    presenter.render();
  }
  
  @Test
  public void askToRenderOnResume() throws Exception {
    final RendersView rendersView = mockery.mock(
        RendersView.class);

    mockery.checking(
        new Expectations() {{
          oneOf(rendersView).render();
        }});

    new BrowseTransactionsActivity(rendersView) {
      // Make this method visible so that I can invoke it
      // in the test.
      //
      // REFACTOR Move to ShadowActivity
      @Override
      public void onResume() {
        super.onResume();
      }
    }.onResume();

    mockery.assertIsSatisfied();
  }
  
  @Test
  public void exportAllTransactionsButtonDoesNotBlowUp()
      throws Exception {
    final BrowseTransactionsActivity
        browseTransactionsActivity
        = Robolectric.buildActivity(BrowseTransactionsActivity.class).create().visible().get();

    final Button exportAllTransactionsButton
        = (Button) browseTransactionsActivity.findViewById(
        R.id
            .exportAllTransactionsButton);
    exportAllTransactionsButton.performClick();
    // don't blow up
  }
}
