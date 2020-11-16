package com.Albenero.Alrethon.service;

import com.Albenero.Alrethon.modal.SmartMeterReading;
import com.Albenero.Alrethon.repository.SmartMeterReadingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Service
public class SmartMeterService {

    @Autowired
    private SmartMeterReadingRepo meterReadingRepo;

    public List<SmartMeterReading> initialDbData(){

        SmartMeterReading reading = new SmartMeterReading();
        reading.setDeviceId("DEVICE_1");
        reading.setDeviceBattery(100.00);
        reading.setEnergyConsumed(0.00);
        reading.setLatitude(17.338055);
        reading.setLongitude(78.471354);
        reading.setTimeStamp(Date.from(Instant.now()));


        SmartMeterReading reading2 = new SmartMeterReading();
        reading2.setDeviceId("DEVICE_2");
        reading2.setDeviceBattery(100.00);
        reading2.setEnergyConsumed(0.00);
        reading2.setLatitude(18.338055);
        reading2.setLongitude(79.471354);
        reading2.setTimeStamp(Date.from(Instant.now()));


        SmartMeterReading reading3 = new SmartMeterReading();
        reading3.setDeviceId("DEVICE_3");
        reading3.setDeviceBattery(100.00);
        reading3.setEnergyConsumed(0.00);
        reading3.setLatitude(19.338055);
        reading3.setLongitude(75.471354);
        reading3.setTimeStamp(Date.from(Instant.now()));

        List<SmartMeterReading> smartMeterReadings = new ArrayList<SmartMeterReading>();

        smartMeterReadings.add(reading);
        smartMeterReadings.add(reading2);
        smartMeterReadings.add(reading3);

        List<SmartMeterReading> result = meterReadingRepo.saveAll(smartMeterReadings);

        return result;
    }

    public List<SmartMeterReading> getAllReadings(){
        return meterReadingRepo.findAll();
    }

    @Scheduled(fixedRate = 300000)
    public void meterData(){

        List<SmartMeterReading> meterReadings = new ArrayList<>();
        List<SmartMeterReading> newMeterReadings = new ArrayList<>();

        meterReadings.add(meterReadingRepo.getLateatReadingOfDev1().get(0));

        meterReadings.add(meterReadingRepo.getLateatReadingOfDev2().get(0));

        meterReadings.add(meterReadingRepo.getLateatReadingOfDev3().get(0));

        for(SmartMeterReading oldReading:meterReadings){

            SmartMeterReading newReading = new SmartMeterReading();

            newReading.setDeviceId(oldReading.getDeviceId());
            newReading.setLongitude(oldReading.getLongitude());
            newReading.setLatitude(oldReading.getLatitude());
            newReading.setDeviceBattery(oldReading.getDeviceBattery());

            if(oldReading.getDeviceId().equals("DEVICE_1")){
               newReading.setEnergyConsumed(oldReading.getEnergyConsumed()+0.17);
            }else if(oldReading.getDeviceId().equals("DEVICE_2")){
                newReading.setEnergyConsumed(oldReading.getEnergyConsumed()+0.11);
            }else{
                newReading.setEnergyConsumed(oldReading.getEnergyConsumed()+0.06);
            }
            newReading.setTimeStamp(Date.from(Instant.now()));
            newMeterReadings.add(newReading);
        }
        meterReadingRepo.saveAll(newMeterReadings);
        System.out.println(newMeterReadings);

    }






}
