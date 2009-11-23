/*
 * Created on Dec 23, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ThrowableAssert#isSameAs(Throwable)}</code>.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_isSameAs_Test implements GenericAssert_isSameAs_TestCase {

  private static Exception actual;

  @BeforeClass
  public static void setUpOnce() {
    actual = new Exception();
  }

  @Test
  public void should_pass_if_actual_and_expected_are_same() {
    new ThrowableAssert(actual).isSameAs(actual);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_same() {
    String message = "expected same instance but found:<java.lang.Exception> and:<java.lang.NullPointerException>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).isSameAs(new NullPointerException());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_same() {
    String msg = "[A Test] expected same instance but found:<java.lang.Exception> and:<java.lang.NullPointerException>";
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).as("A Test")
                                   .isSameAs(new NullPointerException());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).overridingErrorMessage("My custom message")
                                   .isSameAs(new NullPointerException());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).as("A Test")
                                   .overridingErrorMessage("My custom message")
                                   .isSameAs(new NullPointerException());
      }
    });
  }
}