package com.example.spirngaietlpipeline.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ETLTranformerService {

    public List<Document> tranform(List<Document> documents) {
        // Split the documents into tokens
        TextSplitter splitter = new TokenTextSplitter();
        List<Document> splitDocuments = splitter.split(documents);
        return documents;
    }
}
