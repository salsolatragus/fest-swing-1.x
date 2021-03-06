/*
 * Created on Nov 3, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JList;

import org.fest.assertions.Assertions;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

/**
 * Tests for <code>{@link JListItemIndexValidator#validateIndex(JList, int)}</code>.
 *
 * @author Alex Ruiz
 */
public class JListItemIndexValidator_validateIndex_Test extends JListItemIndexValidator_TestCase {

  @Test
  public void should_pass_if_index_is_valid() {
    validateIndex(0);
  }

  @Test
  public void should_throw_error_if_index_is_negative() {
    try {
      validateIndex(-1);
      failWhenExpectingException();
    } catch (IndexOutOfBoundsException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Item index (-1) should be between [0] and [2] (inclusive)");
    }
  }

  @Test
  public void should_throw_error_if_index_is_greater_than_index_of_last_item() {
    try {
      validateIndex(3);
      failWhenExpectingException();
    } catch (IndexOutOfBoundsException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Item index (3) should be between [0] and [2] (inclusive)");
    }
  }

  @RunsInEDT
  private void validateIndex(int index) {
    validateIndex(list, index);
  }

  @RunsInEDT
  private static void validateIndex(final JList list, final int index) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        JListItemIndexValidator.validateIndex(list, index);
      }
    });
  }
}
