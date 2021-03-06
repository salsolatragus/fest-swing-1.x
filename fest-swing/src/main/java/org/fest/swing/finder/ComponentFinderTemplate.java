/*
 * Created on Oct 29, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.finder;

import static org.fest.swing.timing.Pause.pause;
import static org.fest.util.Strings.concat;

import java.awt.Component;
import java.util.concurrent.TimeUnit;

import org.fest.swing.core.*;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.fixture.ComponentFixture;

/**
 * Understands a template for <code>{@link Component}</code> finders.
 * @param <T> the type of component this finder can search.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class ComponentFinderTemplate<T extends Component> {

  static final long TIMEOUT = 5000;

  private long timeout = TIMEOUT;

  private final ComponentMatcher matcher;
  private final String searchDescription;

  /**
   * Creates a new </code>{@link ComponentFinderTemplate}</code>.
   * @param componentName the name of the {@code Component} to find.
   * @param componentType the type of the {@code Component} to find.
   */
  protected ComponentFinderTemplate(String componentName, Class<? extends T> componentType) {
    this(new NameMatcher(componentName, componentType, true));
  }

  /**
   * Creates a new </code>{@link ComponentFinderTemplate}</code>.
   * @param matcher specifies the search criteria to use when looking up a {@code Component}.
   */
  protected ComponentFinderTemplate(GenericTypeMatcher<? extends T> matcher) {
    this((ComponentMatcher)matcher);
  }

  /**
   * Creates a new </code>{@link ComponentFinderTemplate}</code>.
   * @param componentType the type of the {@code Component} to find.
   */
  protected ComponentFinderTemplate(Class<? extends T> componentType) {
    this(new TypeMatcher(componentType, true));
  }

  private ComponentFinderTemplate(ComponentMatcher matcher) {
    if (matcher == null) throw new NullPointerException("The matcher should not be null");
    this.matcher = matcher;
    searchDescription = concat("component to be found using matcher ", matcher);
  }

  /**
   * Sets the timeout for this finder. The {@code Component} to find should be found within the given time period.
   * @param newTimeout the period of time the search should be performed.
   * @param unit the time unit for <code>timeout</code>.
   * @return this finder.
   * @throws NullPointerException if the time unit is {@code null}.
   * @throws IllegalArgumentException if the timeout is a negative number.
   */
  protected ComponentFinderTemplate<T> withTimeout(long newTimeout, TimeUnit unit) {
    if (unit == null) throw new NullPointerException("Time unit cannot be null");
    return withTimeout(unit.toMillis(newTimeout));
  }

  /**
   * Sets the timeout for this finder. The {@code Component} to find should be found within the given time period.
   * @param newTimeout the number of milliseconds before stopping the search.
   * @return this finder.
   * @throws IllegalArgumentException if the timeout is a negative number.
   */
  protected ComponentFinderTemplate<T> withTimeout(long newTimeout) {
    if (newTimeout < 0) throw new IllegalArgumentException("Timeout cannot be a negative number");
    this.timeout = newTimeout;
    return this;
  }

  /**
   * Finds a component by name or type using the given robot.
   * @param robot contains the underlying finding to delegate the search to.
   * @return a fixture capable of managing the found component.
   * @throws WaitTimedOutError if a component with the given name or of the given type could not be found.
   */
  public abstract ComponentFixture<T> using(Robot robot);

  /**
   * Finds the component using either by name or type.
   * @param robot contains the underlying finding to delegate the search to.
   * @return the found component.
   * @throws WaitTimedOutError if a component with the given name or of the given type could not be found.
   */
  protected final T findComponentWith(Robot robot) {
    ComponentFoundCondition condition = new ComponentFoundCondition(searchDescription, robot.finder(), matcher);
    pause(condition, timeout);
    return cast(condition.found());
  }

  /**
   * Casts the given {@code Component} to the type supported by this finder.
   * @param c the given {@code Component}.
   * @return the given {@code Component} casted to the type supported by this finder.
   */
  protected abstract T cast(Component c);
}
