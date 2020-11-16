package com.Albenero.Alrethon.repository;

import com.Albenero.Alrethon.modal.SmartMeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SmartMeterReadingRepo extends JpaRepository<SmartMeterReading, String> {

    String query1 = "select * from SMART_METER_READING where DEVICE_ID = 'DEVICE_1' order by TIME_STAMP DESC limit 1;";

    String query2 ="select * from SMART_METER_READING where TIME_STAMP >= now() - interval '10' minute;";

    @Query(value = query1, nativeQuery = true)
    List<SmartMeterReading> getLateatReadingOfDev1();

    @Query(value = query2, nativeQuery = true)
    List<SmartMeterReading> getReadingByTime();




}
