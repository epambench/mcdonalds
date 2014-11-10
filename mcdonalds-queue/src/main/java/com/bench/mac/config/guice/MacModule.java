package com.bench.mac.config.guice;

import com.bench.mac.api.config.MacOptions;
import com.bench.mac.api.jmx.MacConfiguratorMXBean;
import com.bench.mac.config.MacOptionsImpl;
import com.bench.mac.config.guice.logger.Log4JTypeListener;
import com.bench.mac.jmx.MacConfiguratorMXBeanImpl;
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
    }
}