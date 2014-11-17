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

    private static final Logger logger = LoggerFactory.getLogger(LittleMcDonaldsStore.class);

    private final MacOptions macOptions;
    private MacManager macManager;

    private final Set<List<Client>> clientQueues = new HashSet<>();
    private final Set<Client> fullFedClients = new HashSet<>();
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

    @Override
    public MacOptions getMacOptions() {
        return macOptions;
    }

    @Override
    public MacManager getMacManager() {
        return macManager;
    }

    @Override
    public Set<List<Client>> getClientQueues() {
        return clientQueues;
    }

    @Override
    public Set<Worker> getRegisteredWorkers() {
        return registeredWorkers;
    }

    @Override
    public Set<Client> getFullFedClients() {
        return fullFedClients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void closeStore() {
        opened.set(false);
        logger.info(String.format("\"%s\": CLOSED", name));
    }

    @Override
    public void openStore() {
        logger.info(String.format("\"%s\": We are about to open", name));
        if (opened.get()) {
            throw new IllegalStateException(
                    String.format("\"%s\":  should be closed first in order to be opened!", name));
        }
        macManager = macManagerProvider.get();
        macManager.assignTheStore(this);

        logger.info(String.format("\"%s\": Mac Manager comes to work", name));
        if (getMacOptions() == null) {
            throw new IllegalStateException(
                    String.format("\"%s\": can not be opened with no options configured", name));
        }

        if (getMacManager() == null) {
            throw new IllegalStateException(String.format("\"%s\": can not be opened with no Manager", name));
        }

        if (macOptions.getClients() == null) {
            macOptions.setClients(new int[0]);
        }

        updateClientQueues();
        updateWorkersNumber();
        logger.info(String.format("\"%s\": turning key in the keyhole...", name));
        opened.set(true);
        logger.info(String.format("\"%s\": OPEN!", name));

        while (true) {
            boolean clientExist = macManager.pickClient();
            if (!clientExist) {
                break;
            }
            macManager.pickWorker();
            macManager.orderToProcess();
        }

        closeStore();
    }

    @Override
    public void updateClientQueues() {
        logger.info(String.format("\"%s\": updating number of clients", name));
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
        logger.info(String.format("\"%s\": updating number of clients finished", name));
    }

    @Override
    public void updateWorkersNumber() {
        logger.info(String.format("\"%s\": updating number of workers", name));
        if (registeredWorkers.isEmpty()) {
            for (int personNumber = 1; personNumber <= macOptions.getWorkers(); personNumber++) {
                registeredWorkers.add(workerProvider.get());
            }
        } else {

        }
        logger.info(String.format("\"%s\": updating number of workers", name));
    }
}
