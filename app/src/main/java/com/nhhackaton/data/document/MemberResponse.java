package com.nhhackaton.data.document;

import lombok.Builder;
import lombok.Getter;

@Getter

public class MemberResponse {

    private String url;

    @Builder
    public MemberResponse(String url) {
        this.url = url;
    }
}
