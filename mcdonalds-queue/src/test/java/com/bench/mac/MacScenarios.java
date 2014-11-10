package com.bench.mac;

import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.SilentlyAbsorbingFailure;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true,
        ignoreFailureInStories = true, ignoreFailureInView = false,
        verboseFailures = true)
@UsingSteps(instances = {MacStories.class})
public class MacScenarios extends JUnitStories {

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(
                CodeLocations.codeLocationFromPath("src/main/stories").getFile(),
                "Mc*.story", "");
    }

    @Override
    public void useConfiguration(Configuration configuration) {
        configuration
                .useStoryReporterBuilder(new StoryReporterBuilder()
                                .withFormats(Format.XML, Format.STATS, Format.CONSOLE)
                )
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useFailureStrategy(new SilentlyAbsorbingFailure());
        super.useConfiguration(configuration);
    }
}