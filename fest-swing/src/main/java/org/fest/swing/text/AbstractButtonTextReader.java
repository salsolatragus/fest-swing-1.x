/*
 * Created on Jul 29, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.text;

import javax.swing.AbstractButton;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands reading the text displayed in a <code>{@link AbstractButton}</code>.
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class AbstractButtonTextReader extends TextReader<AbstractButton> {

  /**
   * Indicates that this reader supports {@code AbstractButton}s.
   * @return {@code AbstractButton.class}.
   */
  @Override public Class<AbstractButton> supportedComponent() {
    return AbstractButton.class;
  }

  /**
   * Indicates whether the given {@code AbstractButton} displays the given text.
   * @param button the given {@code AbstractButton}.
   * @param text the given text.
   * @return {@code true} if the given {@code AbstractButton} displays the given text; {@code false} otherwise.
   */
  @RunsInCurrentThread
  @Override protected boolean checkContainsText(AbstractButton button, String text) {
    String labelText = button.getText();
    if (labelText == null) return false;
    return labelText.contains(text);
  }
}
