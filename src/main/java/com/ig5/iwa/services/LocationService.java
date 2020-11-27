package com.ig5.iwa.services;

import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocationService {

    @Autowired
    public LocationRepository locationRepository;

    public Boolean noLocationIdFound(int id){
        return locationRepository.findById(id).isEmpty();
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Optional<Location> findLocationById(Integer id) {
        return locationRepository.findById(id);
    }

    public Location getLocationById(Integer id) {
        return locationRepository.getOne(id);
    }

    public Boolean existsLocationWithLoc(Location location){
        return locationRepository.findAll().stream().filter(l-> (l.getLongitude() == location.getLongitude()) && (l.getLatitude() == location.getLatitude())).count()>0;
    }

    public Location create(float longitude, float latitude, User_Localized currentUser) {
        Location sendNewLoc = new Location(longitude,latitude);
        Set<User_Localized> newList = sendNewLoc.getUsers();
        newList.add(currentUser);
        sendNewLoc.setUsers(newList);
        return locationRepository.saveAndFlush(sendNewLoc);
    }

    public Location save(Location location){
        return locationRepository.save(location);
    }

}
