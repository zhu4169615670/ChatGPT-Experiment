package com.citi.chatgpt.controller;

import com.citi.chatgpt.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/holiday")
public class HolidayController {

    @Autowired
    HolidayService holidayService;

    //API take JSON request to get all holidays
    @RequestMapping("/addHoliday")
    public String addHoliday(@RequestBody List<Map> holiday){
        // if holiday is not null
        if (holiday == null) {
            return "Holiday is null";
        }

        // add holiday to csv file
        holidayService.addHoliday(holiday);
        return "Holiday added";
    }

    //API take JSON request to update holidays
    @RequestMapping("/updateHoliday")
    public String updateHoliday(@RequestBody List<Map> holiday){
        // if holiday is not null
        if (holiday == null) {
            return "Holiday is null";
        }

        // update holiday to csv file
        holidayService.updateHoliday(holiday);
        return "Holiday updated";
    }


    //API take JSON request to delete holidays
    @RequestMapping("/deleteHoliday")
    public String deleteHoliday(@RequestBody String countryCode, @RequestBody String holidayDate){
        // if holidayDate and countryCode are not null
        if (holidayDate == null || countryCode == null) {
            return "Holiday date or country code is null";
        }
        // delete holiday to csv file
        holidayService.deleteHoliday(countryCode, holidayDate);
        return "Holiday deleted";
    }

    //API take JSON request to get next year's holidays
    @RequestMapping("/getNextYearHoliday")
    public String getNextYearHoliday(@RequestBody String countryCode){
        // if countryCode is not null
        if (countryCode == null) {
            return "Country code is null";
        }
        // get next year's holidays
        String holidays = holidayService.getNextYearHoliday(countryCode);
        return holidays;
    }


    //API take JSON request to get next holidays based on given countryCode
    @RequestMapping("/getNextHoliday")
    public String getNextHoliday(@RequestBody String countryCode){
        // if countryCode is not null
        if (countryCode == null) {
            return "Country code is null";
        }
        // get next holidays
        String holidays = holidayService.getNextHoliday(countryCode);
        return holidays;
    }

    // API take JSON request to check if is holidays or not based on given date
    @RequestMapping("/isHoliday")
    public String isHoliday(@RequestBody String date){
        // if date is not null
        if (date == null) {
            return "Date is null";
        }
        // check if is holiday or not
        String isHoliday = holidayService.isHoliday(date);
        return isHoliday;
    }

}

