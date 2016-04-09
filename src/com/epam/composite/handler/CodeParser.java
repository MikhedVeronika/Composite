package com.epam.composite.handler;

import com.epam.composite.entity.ComponentType;
import com.epam.composite.entity.Composite;
import com.epam.composite.service.FileReadService;
import com.epam.composite.service.FileWriteService;
import org.apache.log4j.Logger;

public class CodeParser {
    private static Logger logger = Logger.getLogger(CodeParser.class);

    private Composite element;

    public void parseText(String fileName) {
        logger.info("Parsing text");
        ClassHandler classHandler = new ClassHandler(ComponentType.CLASS);
        BlockHandler blockHandler = new BlockHandler(ComponentType.BLOCK);
        FieldHandler fieldHandler = new FieldHandler(ComponentType.FIELD);
        MethodHandler methodHandler = new MethodHandler(ComponentType.METHOD);
        OperatorHandler operatorHandler = new OperatorHandler(ComponentType.OPERATOR);

        classHandler.setSuccessor(classHandler);
        blockHandler.setSuccessor(blockHandler);
        fieldHandler.setSuccessor(fieldHandler);
        methodHandler.setSuccessor(methodHandler);
        operatorHandler.setSuccessor(operatorHandler);

        element = classHandler.chain(FileReadService.readFromFile(fileName));
    }

    public void writeCodeToFile(String filepath) {
        FileWriteService.writeComposite(filepath, element);
    }
}
