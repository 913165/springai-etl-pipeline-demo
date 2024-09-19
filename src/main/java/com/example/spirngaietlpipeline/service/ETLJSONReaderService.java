package com.example.spirngaietlpipeline.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ETLJSONReaderService {


    @Value("classpath:bikes_data.json")
    Resource resource;

    public List<Document> loadJSONasDocuemnts() {
        JsonReader jsonReader = new JsonReader(resource, "description", "brand");
        return jsonReader.get();
    }
}
