package com.bench.mac.api.jmx;

import javax.management.MXBean;

@MXBean
public interface MacConfiguratorMXBean {
    void openMacDonalds();

    void pauseMac(boolean pause);

    int getClientSize();

    void setClientSize(int size);

    int getConsumerSize();

    void setConsumerSize(int size);
}
