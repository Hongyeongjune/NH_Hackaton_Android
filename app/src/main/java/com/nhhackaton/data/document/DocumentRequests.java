package com.nhhackaton.data.document;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DocumentRequests {

    private String identity;
    private String type;
    private String url;

    @Builder
    public DocumentRequests(String identity, String type, String url) {
        this.identity = identity;
        this.type = type;
        this.url = url;
    }
}
