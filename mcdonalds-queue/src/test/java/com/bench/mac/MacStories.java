package com.bench.mac;

import com.bench.mac.api.config.MacOptions;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import org.junit.Assert;

import javax.inject.Inject;

public class MacStories extends Embedder {

    @Inject
    private MacOptions macOptions;

    @Given("the McDonalds is about to open with director")
    public void macIsAboutToOpen() {
        Assert.assertNotNull("Mac Options should not be null", macOptions);
    }

    @Given("$number consumer")
    public void consumers(final int number) {
        macOptions.setConsumers(number);
    }

    @Given("$number client")
    public void clients(final int number) {
        macOptions.setClients(number);
    }

    @When("McDonalds opens")
    public void macOpens() {

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