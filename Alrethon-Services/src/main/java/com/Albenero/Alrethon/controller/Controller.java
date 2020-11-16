package com.Albenero.Alrethon.controller;

import com.Albenero.Alrethon.modal.ResponseTemplate;
import com.Albenero.Alrethon.service.SmartMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class Controller {

    @Autowired
    private SmartMeterService meterService;

    @RequestMapping(path = "/initData",method = RequestMethod.GET)
    public ResponseTemplate createDbData(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(),meterService.initialDbData());
    }

    @RequestMapping(path = "/getMeterData",method = RequestMethod.GET)
    public ResponseTemplate getAllReadings(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(),meterService.getAllReadings());
    }
}
