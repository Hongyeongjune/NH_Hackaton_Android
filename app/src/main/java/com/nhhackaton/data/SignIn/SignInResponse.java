package com.nhhackaton.data.SignIn;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponse {

    private String accountNum;
    private String bcCd;
    private String birthday;
    private String finAccount;
    private Long id;
    private String identity;
    private String investVirtualAccount;
    private String name;
    private String password;
    private String repaymentVirtualAccount;
    private boolean verified;

    @Builder
    public SignInResponse(String accountNum, String bcCd, String birthday, String finAccount, Long id, String identity, String investVirtualAccount, String name, String password, String repaymentVirtualAccount, boolean verified) {
        this.accountNum = accountNum;
        this.bcCd = bcCd;
        this.birthday = birthday;
        this.finAccount = finAccount;
        this.id = id;
        this.identity = identity;
        this.investVirtualAccount = investVirtualAccount;
        this.name = name;
        this.password = password;
        this.repaymentVirtualAccount = repaymentVirtualAccount;
        this.verified = verified;
    }
}
