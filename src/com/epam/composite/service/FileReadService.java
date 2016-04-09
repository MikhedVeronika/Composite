package com.epam.composite.service;

import org.apache.log4j.Logger;
import java.io.*;
import java.util.Scanner;

public class FileReadService {

    private static Logger logger = Logger.getLogger(FileReadService.class);

    public FileReadService() {
    }

    public static String readFromFile(String filePath) {
        logger.info("Reading class from file");
        StringBuilder builder = new StringBuilder();
        try (Scanner fileInput = new Scanner(new java.io.File(filePath))) {
            while (fileInput.hasNextLine()) {
                builder.append(fileInput.nextLine());
            }
        } catch (IOException ex) {
            logger.fatal("Can not read from file", ex);
            throw new RuntimeException();
        }
        return builder.toString();
    }
}
