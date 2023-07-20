package com.huilian;

public class ChatGPT {

    // calculate days between two dates
    public int daysBetweenDates(String date1, String date2) {
        // convert date1 and date2 to days
        int days1 = convertToDays(date1);
        int days2 = convertToDays(date2);

        // return the difference
        return days2 - days1;
    }


    // convert date to days
    public int convertToDays(String date) {
        // split date into year, month, and day
        String[] dateSplit = date.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);

        // calculate days
        int days = 0;
        for (int i = 1971; i < year; i++) {
            // add 365 days to days
            days += 365;
            // add 1 day if i is a leap year
            if (isLeapYear(i)) {
                days += 1;
            }
        }

        // add days of months
        for (int i = 1; i < month; i++) {
            // add 31 days if month is January, March, May, July, August, October, or December
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12 ) {
                days += 31;
            }
            // add 30 days if month is April, June, September, or November
            else if (i == 4 || i == 6 || i == 9 || i == 11) {
                days += 30;
            }
            // add 28 days if month is February
            else if (i == 2) {
                days += 28;
            }
        }

        // add days
        days += day;

        // return days
        return days;
    }


    // check if year is a leap year
    public boolean isLeapYear(int year) {
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



    // main method  test
    public static void main(String[] args) {
        // create a ChatGPT object
        ChatGPT chat = new ChatGPT();

        // calculate days between 2020-01-15 and 2019-12-31
        int days = chat.daysBetweenDates("2019-12-31", "2020-01-15");

        // print days
        System.out.println(days);
    }

}
