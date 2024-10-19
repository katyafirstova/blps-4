package org.example.services;


import lombok.NonNull;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.entity.Advertisement;
import org.example.entity.User;
import org.example.model.AdvertisementRequest;
import org.example.model.AdvertisementResponse;
import org.example.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {
    @Autowired
    AdvertisementRepository advertisementRepository;

    public void createAdvertisement(@NonNull AdvertisementRequest request, Long owner_id){
        Advertisement newAdvert = new Advertisement();

        newAdvert.setAddress(request.getAddress());
        newAdvert.setDescription(request.getDescription());
        newAdvert.setPrice(request.getPrice());
        newAdvert.setOwner_id(owner_id);
        advertisementRepository.save(newAdvert);
    }

    public AdvertisementResponse getAll(){
        final var res = new AdvertisementResponse(advertisementRepository.findAll().stream().collect(Collectors.toList()));
        return res;
    }

    public Optional<Advertisement> getById(Long id){
        return advertisementRepository.findById(id);
    }

    public Advertisement getAdvertById(Long id){
        Optional<Advertisement> opt = advertisementRepository.findById(id);
        return opt.get();
    }


    public boolean checkExistence(Long id) {
        Optional<Advertisement> opt = advertisementRepository.findById(id);
        if(opt.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    public long getOwnerId(Long id){
        Optional<Advertisement> opt = advertisementRepository.findById(id);
        return opt.get().getOwner_id();
    }

    public boolean checkOwner(Long id, User user){
        Optional<Advertisement> opt = advertisementRepository.findById(id);
        if(opt.isPresent()){
            Advertisement advertisement = opt.get();
            if(advertisement.getOwner_id() != user.getId()){
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }
}
