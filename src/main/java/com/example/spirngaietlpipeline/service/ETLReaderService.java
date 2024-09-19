package com.example.spirngaietlpipeline.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ETLReaderService {


    @Value("classpath:input.txt")
    Resource resource;

    public List<Document> loadTextasDocuemnts() {
        TextReader textReader = new TextReader(resource);
        List<Document> documents = textReader.get();
        return documents;
    }
}
