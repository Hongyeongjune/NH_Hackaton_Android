package com.nhhackaton.data.loan;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class LoanApply {

    private String loanAmount;
    private String studentIdentity;
    private String term;

    public LoanApply(String loanAmount, String studentIdentity, String term) {
        this.loanAmount = loanAmount;
        this.studentIdentity = studentIdentity;
        this.term = term;
    }

    public LoanApply() {
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getStudentIdentity() {
        return studentIdentity;
    }

    public void setStudentIdentity(String studentIdentity) {
        this.studentIdentity = studentIdentity;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}