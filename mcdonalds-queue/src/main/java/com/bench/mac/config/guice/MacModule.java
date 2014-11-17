package com.bench.mac.config.guice;

import com.bench.mac.api.config.MacOptions;
import com.bench.mac.api.jmx.MacConfiguratorMXBean;
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
import com.bench.mac.jmx.MacConfiguratorMXBeanImpl;
import com.bench.mac.model.client.SimpleClientProvider;
import com.bench.mac.model.manager.JuniorMacManagerProvider;
import com.bench.mac.model.store.LittleMcDonaldsStoreProvider;
import com.bench.mac.model.worker.SimpleWorkerProvider;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

public class MacModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MacOptions.class).to(MacOptionsImpl.class).asEagerSingleton();
        bind(MBeanServer.class).toInstance(ManagementFactory.getPlatformMBeanServer());
        bind(MacConfiguratorMXBean.class).to(MacConfiguratorMXBeanImpl.class);

        bindListener(Matchers.any(), new Log4JTypeListener());

        bind(Worker.class).toProvider(SimpleWorkerProvider.class);
        bind(WorkerProvider.class).to(SimpleWorkerProvider.class);

        bind(Client.class).toProvider(SimpleClientProvider.class);
        bind(ClientProvider.class).to(SimpleClientProvider.class);

        bind(MacManager.class).toProvider(JuniorMacManagerProvider.class);
        bind(MacManagerProvider.class).to(JuniorMacManagerProvider.class);

        bind(McDonaldsStore.class).toProvider(LittleMcDonaldsStoreProvider.class);
        bind(StoreProvider.class).to(LittleMcDonaldsStoreProvider.class);
    }
}