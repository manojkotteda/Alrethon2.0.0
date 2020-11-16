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
import java.util.Random;

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

        List<SmartMeterReading> smartMeterReadings = new ArrayList<SmartMeterReading>();
        smartMeterReadings.add(reading);

        List<SmartMeterReading> result = meterReadingRepo.saveAll(smartMeterReadings);

        return result;
    }

    public List<SmartMeterReading> getAllReadings(){
        return meterReadingRepo.findAll();
    }

    public List<SmartMeterReading> getReadingsByTime(){
        return meterReadingRepo.getReadingByTime();
    }

    @Scheduled(fixedRate = 150000)
    public void meterData(){


        Random r = new Random();
        double randomValue = 1 + 4 * r.nextDouble();
        List<SmartMeterReading> meterReadings = new ArrayList<>();
        List<SmartMeterReading> newMeterReadings = new ArrayList<>();

        meterReadings.add(meterReadingRepo.getLateatReadingOfDev1().get(0));
        for(SmartMeterReading oldReading:meterReadings){
            SmartMeterReading newReading = new SmartMeterReading();
            newReading.setDeviceId(oldReading.getDeviceId());
            newReading.setLongitude(oldReading.getLongitude());
            newReading.setLatitude(oldReading.getLatitude());
            newReading.setDeviceBattery(oldReading.getDeviceBattery()-0.01);
            newReading.setEnergyConsumed(randomValue);

            newReading.setTimeStamp(Date.from(Instant.now()));
            newMeterReadings.add(newReading);
        }
        meterReadingRepo.saveAll(newMeterReadings);
        System.out.println(newMeterReadings);

    }






}
