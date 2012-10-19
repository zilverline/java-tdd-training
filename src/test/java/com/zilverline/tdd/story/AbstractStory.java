/*
 * Copyright 2012 SURFnet bv, The Netherlands
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zilverline.tdd.story;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 * AbstractStory.java
 * 
 */
public abstract class AbstractStory extends JUnitStory implements StoryPathResolver {

  @Override
  public Configuration configuration() {
    return new MostUsefulConfiguration().useStoryPathResolver(this).useStoryLoader(new LoadFromClasspath(this.getClass())).useStoryReporterBuilder(
        new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
  }

  @Override
  public InjectableStepsFactory stepsFactory() {
    /*
     * Simple example setup where we expect the Story to contain the steps. If
     * steps need to be shared, more elaborate setup is possible (based on
     * annotations for example)
     */
    return new InstanceStepsFactory(configuration(), this);
  }
  
  @Override
  public String resolve(Class<? extends Embeddable> embeddableClass) {
    return embeddableClass.getSimpleName().replace("Story", "").concat(".story");
  }
}
