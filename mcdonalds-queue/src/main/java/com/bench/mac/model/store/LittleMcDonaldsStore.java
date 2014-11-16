package com.bench.mac.model.store;

import com.bench.mac.api.config.MacOptions;
import com.bench.mac.api.model.Client;
import com.bench.mac.api.model.ClientProvider;
import com.bench.mac.api.model.MacManager;
import com.bench.mac.api.model.MacManagerProvider;
import com.bench.mac.api.model.McDonaldsStore;
import com.bench.mac.api.model.Worker;
import com.bench.mac.api.model.WorkerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class LittleMcDonaldsStore implements McDonaldsStore {

    private Logger logger = LoggerFactory.getLogger(LittleMcDonaldsStore.class);

    private final MacOptions macOptions;
    private MacManager macManager;

    private final Set<List<Client>> clientQueues = new HashSet<>();
    private final Set<Worker> registeredWorkers = new HashSet<>();

    private AtomicBoolean opened = new AtomicBoolean(false);

    private final MacManagerProvider macManagerProvider;
    private final ClientProvider clientProvider;
    private final WorkerProvider workerProvider;

    private final String name;

    LittleMcDonaldsStore(final String name,
                         final MacOptions macOptions,
                         final MacManagerProvider macManagerProvider,
                         final ClientProvider clientProvider,
                         final WorkerProvider workerProvider) {
        this.name = name;
        this.macOptions = macOptions;
        this.macManagerProvider = macManagerProvider;
        this.clientProvider = clientProvider;
        this.workerProvider = workerProvider;
    }

    public MacOptions getMacOptions() {
        return macOptions;
    }

    public MacManager getMacManager() {
        return macManager;
    }

    public Set<List<Client>> getClientQueues() {
        return clientQueues;
    }

    public Set<Worker> getRegisteredWorkers() {
        return registeredWorkers;
    }

    @Override
    public void openStore() {
        logger.info("We are about to open the store");
        if (opened.get()) {
            throw new IllegalStateException("Store should be closed first in order to be opened!");
        }
        macManager = macManagerProvider.get();
        logger.info("Mac Manager comes to work");
        if (getMacOptions() == null) {
            throw new IllegalStateException("Store can not be opened with no options configured");
        }

        if (getMacManager() == null) {
            throw new IllegalStateException("Store can not be opened with no Manager");
        }

        if (macOptions.getClients() == null) {
            macOptions.setClients(new int[0]);
        }

        updateClientQueues();
        updateWorkersNumber();
        logger.info("turning key in the keyhole...");
        opened.set(true);
        logger.info("we are OPEN!");
    }

    @Override
    public void updateClientQueues() {
        logger.info("updating number of clients");
        if (clientQueues.isEmpty()) {
            for (int numberOfPersonsInThisQueue : macOptions.getClients()) {
                ArrayList<Client> clientsInQueue = new ArrayList<>();
                for (int personNumber = 1; personNumber <= numberOfPersonsInThisQueue; personNumber++) {
                    clientsInQueue.add(clientProvider.get());
                }
                clientQueues.add(clientsInQueue);
            }
        } else {

        }
        logger.info("updating number of clients finished");
    }

    @Override
    public void updateWorkersNumber() {
        logger.info("updating number of workers");
        if (registeredWorkers.isEmpty()) {
            for (int personNumber = 1; personNumber <= macOptions.getWorkers(); personNumber++) {
                registeredWorkers.add(workerProvider.get());
            }
        } else {

        }
        logger.info("updating number of workers finished");
    }
}
