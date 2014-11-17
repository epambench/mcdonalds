package com.bench.mac;

import com.bench.mac.api.config.MacOptions;
import com.bench.mac.api.model.Client;
import com.bench.mac.api.model.ClientProvider;
import com.bench.mac.api.model.MacManager;
import com.bench.mac.api.model.MacManagerProvider;
import com.bench.mac.api.model.McDonaldsStore;
import com.bench.mac.api.model.StoreProvider;
import com.bench.mac.api.model.Worker;
import com.bench.mac.api.model.WorkerProvider;
import com.bench.mac.config.MacOptionsImpl;
import com.bench.mac.config.guice.logger.Log4JTypeListener;
import com.bench.mac.model.client.SimpleClientProvider;
import com.bench.mac.model.manager.JuniorMacManagerProvider;
import com.bench.mac.model.store.LittleMcDonaldsStoreProvider;
import com.bench.mac.model.worker.SimpleWorkerProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
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
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@Configure
@RunWith(GuiceAnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true,
        ignoreFailureInStories = true, ignoreFailureInView = false,
        verboseFailures = true)
@UsingGuice(modules = {
        McScenarios.ConfigurationModule.class,
        McScenarios.StepsModule.class,
        McScenarios.MacModuleTest.class
})
public class McScenarios extends InjectableEmbedder {

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
            bind(StoryLoader.class)
                    .toInstance(new LoadFromClasspath(this.getClass().getClassLoader()));
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
            bind(McStories.class).asEagerSingleton();
        }
    }

    public static class MacModuleTest extends AbstractModule {
        @Override
        protected void configure() {
            bindListener(Matchers.any(), new Log4JTypeListener());

            bind(MacOptions.class).to(MacOptionsImpl.class).in(Scopes.NO_SCOPE);

            bind(Worker.class).toProvider(SimpleWorkerProvider.class);
            bind(WorkerProvider.class).to(SimpleWorkerProvider.class);

            bind(Client.class).toProvider(SimpleClientProvider.class);
            bind(ClientProvider.class).to(SimpleClientProvider.class);

            bind(MacManager.class).toProvider(JuniorMacManagerProvider.class);
            bind(MacManagerProvider.class).to(JuniorMacManagerProvider.class);

            bind(McDonaldsStore.class).toProvider(LittleMcDonaldsStoreProvider.class);
            bind(StoreProvider.class).to(LittleMcDonaldsStoreProvider.class).asEagerSingleton();
        }
    }
}