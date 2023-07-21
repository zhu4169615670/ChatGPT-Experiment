package com.citi.chatgpt.service;


import com.citi.chatgpt.data.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HolidayService {
    @Autowired
    DataProcessor dataProcessor;
    public void addHoliday(List<Map> holiday) {
        //API take JSON request to add new holidays
        // get country code, country desc, holiday date, and holiday name from JSON request
        for (Map map : holiday) {
            String countryCode = (String) map.get("country code");
            String countryDesc = (String) map.get("country desc");
            String holidayDate = (String) map.get("holiday date");
            String holidayName = (String) map.get("holiday name");
            // if country code, holiday date are not null
            if (countryCode != null && holidayDate != null) {
                // if country code is valid
                if (countryCode.equals("US") || countryCode.equals("IN") || countryCode.equals("UK")) {
                    // if holiday date is valid
                    if (isValidDate(holidayDate)) {
                        // if holiday name is not null
                        if (holidayName != null) {
                            // add holiday to csv file
                            addHoliday(countryCode, countryDesc, holidayDate, holidayName);
                        }
                    }
                }
            }
        }

    }

    private void addHoliday(String countryCode, String countryDesc, String holidayDate, String holidayName) {
        //API take JSON request to add new holidays
        // add holiday to csv file
        dataProcessor.writeCsv("src/main/resources/holiday.csv", countryCode + "," + countryDesc + "," + holidayDate + "," + holidayName);

    }

    private boolean isValidDate(String holidayDate) {
        //API take JSON request to add new holidays
        // split holiday date by dash
        String[] dateSplit = holidayDate.split("-");
        // if dateSplit has 3 elements
        if (dateSplit.length == 3) {
            // get year, month, and day
            int year = Integer.parseInt(dateSplit[0]);
            int month = Integer.parseInt(dateSplit[1]);
            int day = Integer.parseInt(dateSplit[2]);
            // if year is between 1971 and 2099
            if (year >= 1971 && year <= 2099) {
                // if month is between 1 and 12
                if (month >= 1 && month <= 12) {
                    // if day is between 1 and 31
                    if (day >= 1 && day <= 31) {
                        // if month is January, March, May, July, August, October, or December
                        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ) {
                            return true;
                        }
                        // if month is April, June, September, or November
                        else if (month == 4 || month == 6 || month == 9 || month == 11) {
                            // if day is between 1 and 30
                            if (day >= 1 && day <= 30) {
                                return true;
                            }
                        }
                        // if month is February
                        else if (month == 2) {
                            // if year is a leap year
                            if (isLeapYear(year)) {
                                // if day is between 1 and 29
                                if (day >= 1 && day <= 29) {
                                    return true;
                                }
                            }
                            // if year is not a leap year
                            else {
                                // if day is between 1 and 28
                                if (day >= 1 && day <= 28) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isLeapYear(int year) {
        //API take JSON request to add new holidays
        // check if year is divisible by 4
        if (year % 4 == 0) {
            // check if year is divisible by 100
            if (year % 100 == 0) {
                // check if year is divisible by 400
                if (year % 400 == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        return false;
    }


    public void updateHoliday(List<Map> holiday) {
        // check if country code and holiday date are not null
        for (Map map : holiday) {
            String countryCode = (String) map.get("country code");
            String holidayDate = (String) map.get("holiday date");
            if (countryCode != null && holidayDate != null) {
                // if country code is valid
                if (countryCode.equals("US") || countryCode.equals("IN") || countryCode.equals("UK")) {
                    // if holiday date is valid
                    if (isValidDate(holidayDate)) {
                        // update holiday to csv file
                        updateHoliday(countryCode, holidayDate, map);
                    }
                }
            }
        }
    }

    private void updateHoliday(String countryCode, String holidayDate, Map map) {
        // if country code and holiday date are not null
        if (countryCode != null && holidayDate != null) {
            // if country code is valid
            if (countryCode.equals("US") || countryCode.equals("IN") || countryCode.equals("UK")) {
                // if holiday date is valid
                if (isValidDate(holidayDate)) {
                    // get country desc and holiday name
                    String countryDesc = (String) map.get("country desc");
                    String holidayName = (String) map.get("holiday name");
                    // append map to csv file
                    dataProcessor.writeCsv("src/main/resources/holiday.csv", countryCode + "," + countryDesc + "," + holidayDate + "," + holidayName);
                }
            }
        }
    }

    public void deleteHoliday(String countryCode, String holidayDate) {
        //API take JSON request to delete holidays
        // if country code and holiday date are not null
        if (countryCode != null && holidayDate != null) {
            // if country code is valid
            if (countryCode.equals("US") || countryCode.equals("IN") || countryCode.equals("UK")) {
                // if holiday date is valid
                if (isValidDate(holidayDate)) {
                    // delete holiday to csv file
                    deleteHolidayInCsvFile(countryCode, holidayDate);
                }
            }
        }
    }

    private void deleteHolidayInCsvFile(String countryCode, String holidayDate) {
        List<Map<String, String>> maps = dataProcessor.loadCsv("src/main/resources/holiday.csv");
        // delete holiday in csv file based on country code and holiday date
        for (Map<String, String> map : maps) {
            // get country code and holiday date
            String countryCodeInMap = map.get("country code");
            String holidayDateInMap = map.get("holiday date");
            // if country code and holiday date are not null
            if (countryCodeInMap != null && holidayDateInMap != null) {
                // if country code is equal to country code in map
                if (countryCode.equals(countryCodeInMap)) {
                    // if holiday date is equal to holiday date in map
                    if (holidayDate.equals(holidayDateInMap)) {
                        // remove map from maps
                        maps.remove(map);
                        // write map to csv file
                        dataProcessor.writeCsv("src/main/resources/holiday.csv", countryCode + "," + map.get("country desc") + "," + holidayDate + "," + map.get("holiday name"));
                        break;
                    }
                }
            }
        }

    }

    public String getNextYearHoliday(String countryCode) {
        //API take JSON request to get next year's holidays
        // if country code is not null
        if (countryCode != null) {
            // if country code is valid
            if (countryCode.equals("US") || countryCode.equals("IN") || countryCode.equals("UK")) {
                // get current year
                int currentYear = getCurrentYear();
                // get next year is what year
                int nextYear = getNextYear(currentYear);
                return getNextYearHolidayFromCsvFile(nextYear,countryCode);
            }
        }
        return null;
    }

    private String getNextYearHolidayFromCsvFile(int nextYear, String countryCode) {
        //API take JSON request to get next year's holidays
        // get holidays from csv file
        List<Map<String, String>> maps = dataProcessor.loadCsv("src/main/resources/holiday.csv");
        // get holidays of next year
        List<Map<String, String>> nextYearHolidays = getNextYearHolidays(maps, nextYear, countryCode);
        // convert next year holidays to JSON
        String nextYearHolidaysJson = convertNextYearHolidaysToJson(nextYearHolidays);
        return nextYearHolidaysJson;
    }

    private String convertNextYearHolidaysToJson(List<Map<String, String>> nextYearHolidays) {
        //API take JSON request to get next year's holidays
        // convert next year holidays to JSON
        String nextYearHolidaysJson = "[";
        for (Map<String, String> map : nextYearHolidays) {
            // get country code, country desc, holiday date, and holiday name
            String countryCode = map.get("country code");
            String countryDesc = map.get("country desc");
            String holidayDate = map.get("holiday date");
            String holidayName = map.get("holiday name");
            // convert map to JSON
            String mapJson = "{\"country code\":\"" + countryCode + "\",\"country desc\":\"" + countryDesc + "\",\"holiday date\":\"" + holidayDate + "\",\"holiday name\":\"" + holidayName + "\"}";
            // append mapJson to nextYearHolidaysJson
            nextYearHolidaysJson += mapJson + ",";
        }
        // remove last comma
        nextYearHolidaysJson = nextYearHolidaysJson.substring(0, nextYearHolidaysJson.length() - 1);
        // append ]
        nextYearHolidaysJson += "]";
        return nextYearHolidaysJson;
    }

    private List<Map<String, String>> getNextYearHolidays(List<Map<String, String>> maps, int nextYear, String countryCode) {
        //API take JSON request to get next year's holidays
        // get holidays of next year
        List<Map<String, String>> nextYearHolidays = new ArrayList<>();
        for (Map<String, String> map : maps) {
            // get holiday date
            String holidayDate = map.get("holiday date");
            // if holiday date is not null
            if (holidayDate != null) {
                // if holiday date is valid
                if (isValidDate(holidayDate)) {
                    // get year, month, and day
                    String[] dateSplit = holidayDate.split("-");
                    int year = Integer.parseInt(dateSplit[0]);
                    int month = Integer.parseInt(dateSplit[1]);
                    int day = Integer.parseInt(dateSplit[2]);
                    // if year is next year
                    if (year == nextYear) {
                        // if country code is null
                        if (countryCode == null) {
                            // add map to next year holidays
                            nextYearHolidays.add(map);
                        }
                        // if country code is not null
                        else {
                            // get country code
                            String countryCodeInMap = map.get("country code");
                            // if country code is not null
                            if (countryCodeInMap != null) {
                                // if country code is equal to country code in map
                                if (countryCode.equals(countryCodeInMap)) {
                                    // add map to next year holidays
                                    nextYearHolidays.add(map);
                                }
                            }
                        }
                    }
                }
            }
        }
        return nextYearHolidays;
    }

    private int getNextYear(int currentYear) {
        //API take JSON request to get next year's holidays
        // get next year
        int nextYear = currentYear + 1;
        return nextYear;
    }

    private int getCurrentYear() {
        //API take JSON request to get next year's holidays
        // get current date
        java.util.Date date = new java.util.Date();
        // get current year
        int currentYear = date.getYear() + 1900;
        return currentYear;
    }

    public String getNextHoliday(String countryCode) {
        //API take JSON request to get next holiday based on the current year and date
        // if country code is not null
        if (countryCode != null) {
            // if country code is valid
            if (countryCode.equals("US") || countryCode.equals("IN") || countryCode.equals("UK")) {
                // get current date
                java.util.Date date = new java.util.Date();
                // get current year, month, and day
                int currentYear = date.getYear() + 1900;
                int currentMonth = date.getMonth() + 1;
                int currentDay = date.getDate();
                // get next holiday
                String nextHoliday = getNextHolidayFromCsvFile(currentYear, currentMonth, currentDay, countryCode);
                return nextHoliday;
            }
        }
        return "have no next holiday";
    }

    private String getNextHolidayFromCsvFile(int currentYear, int currentMonth, int currentDay, String countryCode) {
        //API take JSON request to get next holiday based on the current year and date
        // get holidays from csv file
        List<Map<String, String>> maps = dataProcessor.loadCsv("src/main/resources/holiday.csv");
        // get current last holiday base on current year, month, and day
        String currentLastHoliday = getCurrentLastHoliday(maps, currentYear, currentMonth, currentDay, countryCode);
        return currentLastHoliday;
    }

    private String getCurrentLastHoliday(List<Map<String, String>> maps, int currentYear, int currentMonth, int currentDay, String countryCode) {
        //API take JSON request to get next holiday based on the current year and date
        // get current last holiday base on current year, month, and day
        String currentLastHoliday = null;
        for (Map<String, String> map : maps) {
            // get country code and holiday date
            String countryCodeInMap = map.get("country code");
            String holidayDate = map.get("holiday date");
            // if country code and holiday date are not null
            if (countryCodeInMap != null && holidayDate != null) {
                // if country code is equal to country code in map
                if (countryCode.equals(countryCodeInMap)) {
                    // if holiday date is valid
                    if (isValidDate(holidayDate)) {
                        // get year, month, and day
                        String[] dateSplit = holidayDate.split("-");
                        int year = Integer.parseInt(dateSplit[0]);
                        int month = Integer.parseInt(dateSplit[1]);
                        int day = Integer.parseInt(dateSplit[2]);
                        // if year is current year
                        if (year == currentYear) {
                            // if month is current month
                            if (month == currentMonth) {
                                // if day is greater than or equal to current day
                                if (day >= currentDay) {
                                    // if current last holiday is null
                                    if (currentLastHoliday == null) {
                                        // set current last holiday to holiday date
                                        currentLastHoliday = holidayDate;
                                    }
                                    // if current last holiday is not null
                                    else {
                                        // if holiday date is greater than current last holiday
                                        if (holidayDate.compareTo(currentLastHoliday) > 0) {
                                            // set current last holiday to holiday date
                                            currentLastHoliday = holidayDate;
                                        }
                                    }
                                }
                            }
                            // if month is greater than current month
                            else if (month > currentMonth) {
                                // if current last holiday is null
                                if (currentLastHoliday == null) {
                                    // set current last holiday to holiday date
                                    currentLastHoliday = holidayDate;
                                }
                                // if current last holiday is not null
                                else {
                                    // if holiday date is greater than current last holiday
                                    if (holidayDate.compareTo(currentLastHoliday) > 0) {
                                        // set current last holiday to holiday date
                                        currentLastHoliday = holidayDate;
                                    }
                                }
                            }
                        }
                        // if year is greater than current year
                        else if (year > currentYear) {
                            // if current last holiday is null
                            if (currentLastHoliday == null) {
                                // set current last
                                currentLastHoliday = holidayDate;
                            }
                        }
                    }
                }
            }
        }
        return currentLastHoliday;
    }

    public String isHoliday(String date) {
        //API take JSON request to check if a date is a holiday
        // if date is not null
        if (date != null) {
            // if date is formatted correctly based on yyyy-mm-dd
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                // if date is valid
                if (isValidDate(date)) {
                    // get holidays from csv file
                    List<Map<String, String>> maps = dataProcessor.loadCsv("src/main/resources/holiday.csv");
                    // check if date exist in the current maps or not
                    String isHoliday = checkIfDateExistInCurrentMaps(maps, date);
                    return isHoliday;
                }
            }
        }
        return "is not holiday";
    }

    private String checkIfDateExistInCurrentMaps(List<Map<String, String>> maps, String date) {
        //API take JSON request to check if a date is a holiday
        // check if date exist in the current maps or not
        for (Map<String, String> map : maps) {
            // get holiday date
            String holidayDate = map.get("holiday date");
            // if holiday date is not null
            if (holidayDate != null) {
                // if holiday date is equal to date
                if (holidayDate.equals(date)) {
                    return "is holiday in countryCode: " + map.get("country code");
                }
            }
        }
        return "is not holiday in any country";
    }
}
