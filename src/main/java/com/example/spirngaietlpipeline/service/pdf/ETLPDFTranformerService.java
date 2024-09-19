package com.example.spirngaietlpipeline.service.pdf;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.KeywordMetadataEnricher;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ETLPDFTranformerService {

    @Autowired
    ChatModel chatModel;

    public List<Document> tranform(List<Document> documents) {
        // Split the documents into tokens
        TextSplitter splitter = new TokenTextSplitter();
        List<Document> splitDocuments = splitter.split(documents);

        // enrish the documents with chat model and KeyWords

        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(chatModel,5   );
        //SummaryMetadataEnricher summaryMetadataEnricher = new SummaryMetadataEnricher(chatModel,List.of(SummaryMetadataEnricher.SummaryType.CURRENT );
        //return summaryMetadataEnricher.apply(splitDocuments);
        return keywordMetadataEnricher.apply(splitDocuments);
    }
}
