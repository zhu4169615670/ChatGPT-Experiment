package com.citi.chatgpt.data;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataProcessor {

        private List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        // loading a csv file and storing data in a list of maps
        public List<Map<String, String>> loadCsv(String csvPath) {
            // read csv file
            try (BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {
                // read header
                String header = csvReader.readLine();
                // read each line
                String row;
                while ((row = csvReader.readLine()) != null) {
                    // split row by comma
                    String[] data = row.split(",");
                    // add data to map
                    Map<String, String> map = new HashMap<>();
                    map.put("country code", data[0]);
                    map.put("country desc", data[1]);
                    map.put("holiday date", data[2]);
                    map.put("holiday name", data[3]);
                    // add map to list
                    list.add(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;


        }

        // write data to csv file
        public void writeCsv(String csvPath, String content) {
            // write list to csv file based on csvPath
            File file = new File(csvPath);
            // if file does not exist
            if (!file.exists()) {
                // create file
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // write content to csv file
            try (java.io.FileWriter csvWriter = new java.io.FileWriter(csvPath, true)) {
                csvWriter.append(content);
                csvWriter.append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }






}
