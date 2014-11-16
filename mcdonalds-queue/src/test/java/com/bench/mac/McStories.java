package com.bench.mac;

import com.bench.mac.api.model.McDonaldsStore;
import com.bench.mac.api.model.StoreProvider;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import org.junit.Assert;

import javax.inject.Inject;
import java.util.Arrays;

public class McStories extends Embedder {

    @Inject
    private StoreProvider storeProvider;
    private McDonaldsStore mcDonaldsStore;

    @Given("the McDonalds is about to open")
    public void macIsAboutToOpen() {
        mcDonaldsStore = storeProvider.get();
        Assert.assertNotNull("McDonalds store should not be null", mcDonaldsStore);
        Assert.assertNotNull("McDonalds should be initialized with Mac Options", mcDonaldsStore.getMacOptions());
    }

    @Given("$number {consumer|consumers}")
    public void consumers(final int number) {
        mcDonaldsStore.getMacOptions().setWorkers(number);
    }

    @Given("$number {client|clients} in first queue")
    public void clientsInFirstQueue(final int number) {
        int[] clients = mcDonaldsStore.getMacOptions().getClients();
        if (clients.length == 0) {
            clients = new int[1];
        }
        clients[0] = number;
        mcDonaldsStore.getMacOptions().setClients(clients);
    }

    @Given("$number {client|clients} in second queue")
    public void clientsInSecondQueue(final int number) {
        int[] clients = mcDonaldsStore.getMacOptions().getClients();
        if (clients.length <= 1) {
            clients = Arrays.copyOf(clients, 2);
        }
        clients[1] = number;
        mcDonaldsStore.getMacOptions().setClients(clients);
    }

    @When("McDonalds opens")
    public void macOpens() {
        mcDonaldsStore.openStore();
    }

    @When("$number client comes in")
    public void whenClientComes(final int number) {

    }

    @When("$consumerNumber consumer takes $clientNumber client")
    public void whenConsumerTakesClient(final int consumerNumber, final int clientNumber) {

    }

    @When("$consumerNumber consumer has processed $clientNumber client")
    public void whenConsumerProcessedClient(final int consumerNumber, final int clientNumber) {

    }

    @Then("$number client comes in")
    public void thenClientComes(final int number) {

    }

    @Then("$consumerNumber consumer takes $clientNumber client")
    public void thenConsumerTakesClient(final int consumerNumber, final int clientNumber) {

    }

    @Then("$number client awaiting outside")
    public void thenNumberOfClientAwaitingOutside() {

    }

    @Then("$number consumer waiting for client")
    public void thenNumberOfConsumerWaitingForClient() {

    }

    @Then("$consumerNumber consumer is working with $clientNumber client")
    public void thenNumberOfConsumerWorkingWithNumberOfClient(final int consumerNumber, final int clientNumber) {

    }
}