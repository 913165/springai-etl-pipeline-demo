package com.example.spirngaietlpipeline.controller;

import com.example.spirngaietlpipeline.service.ETLReaderService;
import com.example.spirngaietlpipeline.service.ETLTranformerService;
import com.example.spirngaietlpipeline.service.ETLWriterService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ETLControler {

    @Autowired
    ETLReaderService etlReaderService;

    @Autowired
    ETLTranformerService etlTranformerService;

    @Autowired
    ETLWriterService etlWriterService;

    @GetMapping("/etl")
    public String etl() {
        List<Document> documents = etlReaderService.loadTextasDocuemnts();
        List<Document> transformedDocuments = etlTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL Pipeline completed";
    }
}
