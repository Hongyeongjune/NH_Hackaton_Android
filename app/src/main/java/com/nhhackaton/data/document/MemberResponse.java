package com.nhhackaton.data.document;

import lombok.Builder;
import lombok.Getter;

public class MemberResponse {

    private String url;

    public String getUrl() {
        return url;
    }

    public MemberResponse(String url) {
        this.url = url;
    }
}
