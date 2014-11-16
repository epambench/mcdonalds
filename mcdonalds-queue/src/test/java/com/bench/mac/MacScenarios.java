package com.bench.mac;

import com.bench.mac.api.config.MacOptions;
import com.bench.mac.config.MacOptionsImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.guice.UsingGuice;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.junit.guice.GuiceAnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.StepPatternParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.List;

@Configure
@RunWith(GuiceAnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true,
        ignoreFailureInStories = true, ignoreFailureInView = false,
        verboseFailures = true)
@UsingGuice(modules = {
        MacScenarios.ConfigurationModule.class,
        MacScenarios.StepsModule.class,
        MacScenarios.MacModuleTest.class
})
public class MacScenarios extends InjectableEmbedder {

    @Test
    @Override
    public void run() throws Throwable {
        injectedEmbedder().runStoriesAsPaths(storyPaths());
    }

    private List<String> storyPaths() {
        return new StoryFinder().findPaths(
                CodeLocations.codeLocationFromPath("src/main/stories").getFile(),
                "Mc*.story", "");
    }

    public static class ConfigurationModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(StepPatternParser.class)
                    .toInstance(new RegexPrefixCapturingPatternParser("%"));
            bind(StoryLoader.class)
                    .toInstance(new LoadFromClasspath(this.getClass().getClassLoader()));
            bind(ParameterConverters.ParameterConverter.class)
                    .toInstance(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")));
            bind(StoryReporterBuilder.class)
                    .toInstance(new StoryReporterBuilder()
                            .withDefaultFormats()
                            .withFormats(Format.CONSOLE, Format.HTML, Format.TXT, Format.XML)
                            .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
                            .withFailureTrace(true));
        }

    }

    public static class StepsModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(MacStories.class).in(Scopes.SINGLETON);
        }
    }

    public static class MacModuleTest extends AbstractModule {
        @Override
        protected void configure() {
            bind(MacOptions.class).to(MacOptionsImpl.class).in(Scopes.NO_SCOPE);
        }
    }
}