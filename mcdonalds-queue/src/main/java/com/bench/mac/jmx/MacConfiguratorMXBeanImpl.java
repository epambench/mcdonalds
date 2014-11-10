package com.bench.mac.jmx;

import com.bench.mac.api.jmx.MacConfiguratorMXBean;
import com.bench.mac.api.config.MacOptions;

import javax.inject.Inject;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MacConfiguratorMXBeanImpl implements MacConfiguratorMXBean {

    private MacOptions macOptions;

    @Inject
    private MacConfiguratorMXBeanImpl(MacOptions macOptions, MBeanServer mBeanServer) {
        this.macOptions = macOptions;
        try {
            ObjectName name = new ObjectName("MacDonaldsApp:type=MacConfiguratorMXBeanImpl");
            mBeanServer.registerMBean(this, name);
        } catch (JMException e) {
            e.printStackTrace();
        }
    }

    public void openMacDonalds() {
        System.out.println("Mac opened!");
    }

    @Override
    public void pauseMac(boolean pause) {
        System.out.println("Mac pause status changed to " + pause);
    }

    public int getClientSize() {
        return macOptions.getClients();
    }

    @Override
    public void setClientSize(int size) {
        macOptions.setClients(size);
        System.out.println("Client size now " + size);
    }

    @Override
    public int getConsumerSize() {
        return macOptions.getConsumers();
    }

    public void setConsumerSize(int size) {
        macOptions.setConsumers(size);
        System.out.println("Customer size now " + size);
    }
}
