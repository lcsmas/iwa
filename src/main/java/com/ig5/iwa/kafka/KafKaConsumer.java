package com.ig5.iwa.kafka;

import com.ig5.iwa.models.User;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.services.NotificationService;
import com.ig5.iwa.services.UserLocalizedService;
import com.ig5.iwa.services.UserStateService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;

import static java.util.concurrent.TimeUnit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class KafKaConsumer {

    @Autowired
    KafkaController kafkaController;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserStateService userStateService;

    @Autowired
    private UserLocalizedService userLocalizedService;

    private static long MAX_DURATION = MILLISECONDS.convert(15, SECONDS);
    private static double MAX_DISTANCE = 500;
    private Date lastDateMessage;
    private List<String[]> locationStack = new ArrayList<>();

    // Send message to client through Emitter
    public SseEmitter latestEm = kafkaController.getLatestEmitter();

    public KafKaConsumer() {
        this.lastDateMessage = new Date();
    }

    @KafkaListener(topics = "iwaTopic", groupId = "consumerId")
    public void consume(String message) {
        System.out.println("#### -> Consumed message -> " + message);

        /*  0 : userid
            1 : state,
            2: latitude,
            3: longitude
            4: timestamp
         */
        String[] location = message.split(",");
        Date dateMessage = new Date(Long.parseLong(location[4]));

        long diffTime = dateMessage.getTime() - lastDateMessage.getTime();

        // moins de 15 secondes
        if (diffTime < MAX_DURATION) {
            System.out.println(" ----- de 15 secondes");
            locationStack.add(location);
        } else {
            System.out.println("++++++ de 15 min");
            lastDateMessage = dateMessage;
            compareLocation();
        }

    }

    public void compareLocation(){

        for(int i=0; i<locationStack.size()-1; i++){
            String[] currentlocation = locationStack.get(i);


            for(int j=i+1; j<locationStack.size(); j++){
                String[] location2 = locationStack.get(j);

                if(!sameUser(currentlocation,location2) && suspicious(location2) && inPerimeter(currentlocation,location2)){
                    //create Notification an persiste location of current
                    System.out.println("------------------------- Contact avec une personne malade ----------------------------");

                    int idCurrentUser = Integer.parseInt(currentlocation[0]);
                    int idUserCovid = Integer.parseInt( location2[0]);
                    int idState = userStateService.getIdStateOfUserState(idUserCovid).orElse(-1);
                    float latCovid = Float.parseFloat(location2[2]);
                    float longCovid = Float.parseFloat(location2[3]);
                    int idLocationCovid = userLocalizedService.save(idUserCovid,longCovid,latCovid);
                    notificationService.createNot(idCurrentUser,idState,idLocationCovid,"Contact avec une personne malade");
                }
            }
        }
    }




    public boolean suspicious(String[] location2){
        boolean res = location2[1].equals("covid");
        System.out.println("suspicious:" + res);
        return res;
    }


    //check if same user
    public boolean sameUser(String [] location1,String[]location2){
        boolean res = location1[0].equals(location2[0]);
        System.out.println("sameUser:" + res);
        return res;
    }

    /*
        0 : userid
        1 : state,
        2: latitude,
        3: longitude
        4: timestamp
    */
    public Boolean inPerimeter(String [] location1,String[]location2){
        double latitude1 = Double.parseDouble(location1[2]);
        double latitude2 = Double.parseDouble(location2[2]);
        double longitude1 = Double.parseDouble(location1[3]);
        double longitude2 = Double.parseDouble(location2[3]);

        double distance = distance(latitude1,latitude2,longitude1,longitude2);

        boolean res = distance < MAX_DISTANCE;
        System.out.println("inPerimeter:" + res);
        return res;
    }


    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2) ;

        return Math.sqrt(distance);
    }

    //persist if user is positif
    // Send message to client through Emitter
    /*public void sendNotification() throws IOException{
        try {
            latestEm.send("message");
        } catch (IOException e) {
            latestEm.completeWithError(e);
        }
        //ConsumerRecord data = (ConsumerRecord) object;
        //KafkaObject o = (KafkaObject) data.value();
        //System.out.println(o);
    }*/

}
