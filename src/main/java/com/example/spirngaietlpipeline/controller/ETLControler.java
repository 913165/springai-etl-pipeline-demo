package com.example.spirngaietlpipeline.controller;

import com.example.spirngaietlpipeline.service.*;
import com.example.spirngaietlpipeline.service.pdf.ETLPDFReaderService;
import com.example.spirngaietlpipeline.service.pdf.ETLPDFTranformerService;
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
    ETLPDFReaderService etlPDFReaderService;

    @Autowired
    ETLPDFTranformerService etlPDFTranformerService;

    @Autowired
    ETLJSONReaderService etlJSONReaderService;

    @Autowired
    ETLWriterService etlWriterService;

    @Autowired
    ETLJSONTranformerService etlJSONTranformerService;

    @GetMapping("/etl")
    public String etl() {
        List<Document> documents = etlReaderService.loadTextasDocuemnts();
        List<Document> transformedDocuments = etlTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL Pipeline completed";
    }

    @GetMapping("/etlpdf")
    public String etlpdf() {
        List<Document> documents = etlPDFReaderService.loadTextasDocuemnts();
        List<Document> transformedDocuments = etlPDFTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL Pipeline completed";
    }

    @GetMapping("/etljson")
    public String etlJson() throws InterruptedException {
        List<Document> documents = etlJSONReaderService.loadJSONasDocuemnts();
        List<Document> transformedDocuments = etlJSONTranformerService.tranform(documents);
        etlWriterService.write(transformedDocuments);
        return "ETL Pipeline completed";
    }
}
