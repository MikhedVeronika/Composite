package com.epam.composite.service;

import com.epam.composite.entity.Composite;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriteService {
    private static Logger logger = Logger.getLogger(FileWriteService.class);

    public FileWriteService() {
    }

    public static boolean writeOperators(String filePath, ArrayList<String> operatorsList) {
        logger.info("Writing sorted operators to file");
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new java.io.File(filePath))))) {
            writer.println("List of operators sorted by lexemes count: \n");
            for (String anOperatorsList : operatorsList) {
                writer.println(anOperatorsList);
            }
            result = true;
        } catch (IOException ex) {
            logger.fatal("Can not write to file", ex);
        }
        return result;
    }

    public static boolean writeLexemesDeletedFirstLetter(String filePath, ArrayList<String> lexemesList, ArrayList<String> newLexemesList) {
        logger.info("Writing modified lexemes to file");
        if (lexemesList.size() != newLexemesList.size()) {
            return false;
        }
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new java.io.File(filePath))))) {
            writer.println("Deleting first letters in lexemes: \n");
            for (int i = 0; i < lexemesList.size(); i++) {
                writer.println(lexemesList.get(i) + "     ->     " + newLexemesList.get(i));
            }
            result = true;
        } catch (IOException ex) {
            logger.fatal("Can not write to file", ex);
        }
        return result;
    }

    public static boolean writeLexemesSortedByAlphabet(String filePath, ArrayList<String> lexemeList) {
        logger.info("Writing sorted lexemes to file");
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new java.io.File(filePath))))) {
            writer.println("Lexemes sorted by alphabet: \n");
            for (int i = 0; i < lexemeList.size(); i++) {
                writer.println(lexemeList.get(i));
            }
            result = true;
        } catch (IOException ex) {
            logger.fatal("Can not write to file", ex);
        }
        return result;
    }

    public static boolean writeComposite(String filePath, Composite composite) {
        logger.info("Writing composite to file");
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new java.io.File(filePath))))) {
            writer.println("File content: \n");
            writer.println(composite.toString());
            result = true;
        } catch (IOException ex) {
            logger.fatal("Can not write to file", ex);
        }
        return result;
    }
}
