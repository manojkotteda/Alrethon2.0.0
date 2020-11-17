package com.Albenero.Alrethon.controller;

import com.Albenero.Alrethon.modal.ResponseTemplate;
import com.Albenero.Alrethon.service.SmartMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
public class Controller {

    @Autowired
    private SmartMeterService meterService;

    @RequestMapping(path = "/initData",method = RequestMethod.GET)
    public ResponseTemplate createDbData(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(),meterService.initialDbData());
    }

    @RequestMapping(path = "/initUser",method = RequestMethod.GET)
    public ResponseTemplate createUser(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(),meterService.initialUser());
    }

    @RequestMapping(path = "/getMeterData",method = RequestMethod.GET)
    public ResponseTemplate getAllReadings(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(),meterService.getAllReadings());
    }
    @RequestMapping(path = "/getMeterDataByTime",method = RequestMethod.GET)
    public ResponseTemplate getAllReadingsByTime(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(),meterService.getReadingsByTime());
    }

    @RequestMapping(path = "/setThreshold",method = RequestMethod.GET)
    public ResponseTemplate getAllReadingsByTime(@RequestParam(name = "threshold") Double aDouble){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(),meterService.setUserThreshold(aDouble));
    }


}
