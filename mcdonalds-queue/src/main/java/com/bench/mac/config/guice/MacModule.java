package com.bench.mac.config.guice;

import com.bench.mac.api.jmx.MacConfiguratorMXBean;
import com.bench.mac.config.MacOptionsImpl;
import com.bench.mac.jmx.MacConfiguratorMXBeanImpl;
import com.bench.mac.api.config.MacOptions;
import com.google.inject.AbstractModule;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

public class MacModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MacOptions.class).to(MacOptionsImpl.class).asEagerSingleton();
        bind(MBeanServer.class).toInstance(ManagementFactory.getPlatformMBeanServer());
        bind(MacConfiguratorMXBean.class).to(MacConfiguratorMXBeanImpl.class);
    }
}