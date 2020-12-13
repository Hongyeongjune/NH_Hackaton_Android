package com.nhhackaton.data.Account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    private String acno;
    private String bncd;

    @Builder
    public Account(String acno, String bncd) {
        this.acno = acno;
        this.bncd = bncd;
    }

}
