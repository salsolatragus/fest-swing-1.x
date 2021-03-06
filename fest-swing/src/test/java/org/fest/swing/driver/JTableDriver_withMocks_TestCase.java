/*
 * Created on Apr 2, 2010
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
package org.fest.swing.driver;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.core.TestRobots.singletonRobotMock;

import javax.swing.JTable;

import org.junit.BeforeClass;

/**
 * Base test case for <code>{@link JTableDriver}</code> that uses mocks as part of its fixture.
 *
 * @author Alex Ruiz
 */
public class JTableDriver_withMocks_TestCase {

  static JTableDriver driver;
  static JTable table;

  @BeforeClass
  public static void setUpOnce() {
    driver = new JTableDriver(singletonRobotMock());
    table = createMock(JTable.class);
  }
}
