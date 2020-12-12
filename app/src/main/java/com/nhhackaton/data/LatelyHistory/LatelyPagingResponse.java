package com.nhhackaton.data.LatelyHistory;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LatelyPagingResponse {

    private List<LatelyHistory> latelyHistories = new ArrayList<>();

    @Builder
    public LatelyPagingResponse(List<LatelyHistory> latelyHistories) {
        this.latelyHistories = latelyHistories;
    }
}
