package org.example.delegators;

import camundajar.impl.scala.Int;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.model.AdvertisementRequest;
import org.example.services.AdvertisementService;


//@Named("createAdvDelegator")
public class CreateAdvDelegator implements JavaDelegate {

    AdvertisementService advertisementService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String address = (String) execution.getVariable("address");
        String description = (String) execution.getVariable("description");
        Integer price = (Integer) execution.getVariable("price");
        Long ownerId = (Long) execution.getVariable("owner");

        System.out.println("New advert: " + address + " " + description + " " + price);
        advertisementService.createAdvertisement(new AdvertisementRequest(address, description, price), ownerId);
    }
}
