package com.bench.mac.model.manager;

import com.bench.mac.api.model.Client;
import com.bench.mac.api.model.MacManager;
import com.bench.mac.api.model.McDonaldsStore;
import com.bench.mac.api.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

class JuniorMacManager implements MacManager {
    private static final Logger logger = LoggerFactory.getLogger(JuniorMacManager.class);

    private final String name;

    private McDonaldsStore mcDonaldsStore;

    private Client client;
    private Worker worker;

    JuniorMacManager(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void assignTheStore(McDonaldsStore mcDonaldsStore) {
        this.mcDonaldsStore = mcDonaldsStore;
    }

    @Override
    public boolean pickClient() {
        client = getNextClient();
        if (client == null) {
            return false;
        }
        logger.info(String.format("\"%s\": got a client \"%s\"", name, client.getName()));
        return true;

    }

    @Override
    public void pickWorker() {
        worker = getFreeWorker();
        logger.info(String.format("\"%s\": got a worker \"%s\"", name, worker.getName()));
    }

    @Override
    public void orderToProcess() {
        worker.processClient(client);
        client.eat();
        mcDonaldsStore.getFullFedClients().add(client);
    }

    private Worker getFreeWorker() {
        Worker worker;
        OUT:
        while (true) {
            for (Worker registeredWorker : mcDonaldsStore.getRegisteredWorkers()) {
                if (!registeredWorker.busy()) {
                    worker = registeredWorker;
                    break OUT;
                }
            }
        }
        return worker;
    }

    private Client getNextClient() {
        Client client = null;
        Iterator<List<Client>> clientQueuesIterator = mcDonaldsStore.getClientQueues().iterator();
        while (clientQueuesIterator.hasNext()) {
            List<Client> clientList = clientQueuesIterator.next();
            Iterator<Client> clientIterator = clientList.iterator();
            if (clientIterator.hasNext()) {
                client = clientIterator.next();
                clientIterator.remove();
                break;
            } else {
                clientQueuesIterator.remove();
            }
        }
        return client;
    }
}
