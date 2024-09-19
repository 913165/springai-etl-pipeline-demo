package com.example.spirngaietlpipeline.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.KeywordMetadataEnricher;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ETLJSONTranformerService {

    @Autowired
    ChatModel chatModel;

    public List<Document> tranform(List<Document> documents) throws InterruptedException {
        // Split the documents into tokens
        TextSplitter splitter = new TokenTextSplitter();
        List<Document> splitDocuments = splitter.split(documents);

        // enrish the documents with chat model and KeyWords

       // KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(chatModel,5   );

        return apply(splitDocuments);
    }


    public List<Document> apply(List<Document> documents) throws InterruptedException {
        for (Document document : documents) {
            var template = new PromptTemplate(String.format(KeywordMetadataEnricher.KEYWORDS_TEMPLATE, 5));
            Prompt prompt = template.create(Map.of(KeywordMetadataEnricher.CONTEXT_STR_PLACEHOLDER, document.getContent()));
            TimeUnit.SECONDS.sleep(20);
            String keywords = this.chatModel.call(prompt).getResult().getOutput().getContent();
            System.out.println("Extracted keywords: " + keywords);
            document.getMetadata().putAll(Map.of("excerpt_keywords", keywords));
        }
        return documents;
    }
}
