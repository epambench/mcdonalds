package com.bench.mac.jmx;

import com.bench.mac.api.config.MacOptions;
import com.bench.mac.api.jmx.MacConfiguratorMXBean;
import com.bench.mac.config.guice.logger.InjectLogger;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MacConfiguratorMXBeanImpl implements MacConfiguratorMXBean {

    @InjectLogger
    private Logger logger;

    private MacOptions macOptions;

    @Inject
    private MacConfiguratorMXBeanImpl(MacOptions macOptions, MBeanServer mBeanServer) {
        this.macOptions = macOptions;
        try {
            ObjectName name = new ObjectName("MacDonaldsApp:type=MacConfiguratorMXBeanImpl");
            mBeanServer.registerMBean(this, name);
        } catch (JMException e) {
            logger.error("Error publishing MXBean", e);
        }
    }

    public void openMacDonalds() {
        logger.debug("Mac opened!");
    }

    @Override
    public void pauseMac(boolean pause) {
        logger.debug("Mac pause status changed to {}", pause);
    }

    public int[] getClientSize() {
        return macOptions.getClients();
    }

    @Override
    public void setClientSize(int[] size) {
        macOptions.setClients(size);
        logger.debug("Client size now {}", size);
    }

    @Override
    public int getConsumerSize() {
        return macOptions.getWorkers();
    }

    public void setConsumerSize(int size) {
        macOptions.setWorkers(size);
        logger.debug("Customer size now {}", size);
    }
}
