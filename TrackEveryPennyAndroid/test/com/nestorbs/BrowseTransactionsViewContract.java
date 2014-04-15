package com.nestorbs;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;




import org.junit.Test;

import com.nestorbs.toolkit.ProgrammerMistake;

public abstract class BrowseTransactionsViewContract {
  @Test
  public void rejectNegativeNumber() throws Exception {
    final BrowseTransactionsView browseTransactionsView
        = initializeView();

    try {
      browseTransactionsView
          .displayNumberOfTransactions(-1);

      fail(
          "Why did you display a negative number of " +
          "transactions?! That's crazy talk!");
    } catch (ProgrammerMistake success) {
      assertTrue(
          success.getCause()
              instanceof IllegalArgumentException);
    }
  }

  protected abstract BrowseTransactionsView initializeView();
}
