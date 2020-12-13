package com.nhhackaton.network.api;

import com.nhhackaton.data.Account.Account;
import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.data.Invest.Invest;
import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;
import com.nhhackaton.data.InvestHistory.InvestDeposit.InvestDeposit;
import com.nhhackaton.data.InvestHistory.InvestFinish.InvestFinish;
import com.nhhackaton.data.InvestHistory.InvestPagingResponse;
import com.nhhackaton.data.LatelyHistory.LatelyInterest.LatelyInterestHistory;
import com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.LatelyInvestDeposit;
import com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.LatelyInvestFinish;
import com.nhhackaton.data.LatelyHistory.LatelyPagingResponse;
import com.nhhackaton.data.LoanHistory.LoanMoney.LoanMoney;
import com.nhhackaton.data.LoanHistory.LoanPagingResponse;
import com.nhhackaton.data.LoanHistory.repayment.Repayment;
import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.data.SignIn.SignInResponse;
import com.nhhackaton.data.SignUp.SignUp;
import com.nhhackaton.data.document.DocumentRequests;
import com.nhhackaton.data.document.MemberResponse;
import com.nhhackaton.data.loan.LoanApply;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitApiService {

    /**
     * 로그인
     */
    @POST("member/signin")
    Call<SignInResponse> callSignIn(@Body SignIn signIn);

    /**
     * 회원가입
     */
    @POST("member/signup")
    Call<Void> callSignUp(@Body SignUp signUp);

    /**
     * 중복 확인
     */
    @GET("member/duplication/{identity}")
    Call<Boolean> callDuplicate(@Path("identity") String identity);

    /**
     * 예치금 조회
     */
    @GET("deposit/{identity}")
    Call<DepositMoney> callReadDepositMoney(@Path("identity") String identity);

    /**
     * 투자 신청
     */
    @POST("deposit/apply-invest/{identity}")
    Call<Void> callApplyMoney(@Path("identity") String identity, @Body Invest invest);

    /**
     * 대출 현황
     * 최근 거래 내역 조회
     */
    @POST("/")
    Call<InvestPagingResponse> callReadInvestHistory(@Body int pageNo);
    @POST("/")
    Call<LoanPagingResponse> callReadLoanHistory(@Body int pageNo);
    @POST("/")
    Call<LatelyPagingResponse> callReadLatelyHistory();

    /**
     * 이자 상환 리스트
     * 대출 금 조회
     */
    @GET("loan/borrower/{identity}")
    Call<List<Repayment>> callReadInterestBorrow(@Path("identity") String identity);
    @GET("loan/{identity}")
    Call<List<LoanMoney>> callReadLoanMoney(@Path("identity") String identity);

    /**
     * 투자 완료 리스트
     * 투자 신청 리스트
     * 투자자 아이디를 통해서 투자자가 받은 이자 목록 보기
     */
    @GET("deposit/already-invest-list/{identity}")
    Call<List<InvestFinish>> callReadInvestFinish(@Path("identity") String identity);
    @GET("deposit/apply-invest-list/{identity}")
    Call<List<InvestDeposit>> callReadInvestDeposit(@Path("identity") String identity);
    @GET("loan/investor/{identity}")
    Call<List<InterestHistory>> callReadInterest(@Path("identity") String identity);

    /**
     * 투자 완료 리스트
     * 투자 신청 리스트
     * 투자자 아이디를 통해서 투자자가 받은 이자 목록 보기
     */
    @GET("deposit/already-invest-list/{identity}")
    Call<List<LatelyInvestFinish>> callLatelyReadInvestFinish(@Path("identity") String identity);
    @GET("deposit/apply-invest-list/{identity}")
    Call<List<LatelyInvestDeposit>> callLatelyReadInvestDeposit(@Path("identity") String identity);
    @GET("loan/investor/{identity}")
    Call<List<LatelyInterestHistory>> callLatelyReadInterest(@Path("identity") String identity);

    /**
     * 대출 신청
     */
    @POST("loan")
    Call<Void> callLoanApply(@Body LoanApply loanApply);
    @POST("member")
    Call<Void> callSetDocument(@Body List<DocumentRequests> documentRequests);

    /**
     * 계좌 등록
     */
    @POST("member/set-account/{identity}")
    Call<Void> callSetAccount(@Path("identity")String identity, @Body Account account);

    /**
     * 파일 업로드 URI 전송
     */
    @Multipart
    @POST("member/document/{identity}")
    Call<MemberResponse> callGetUri(@Path("identity") String identity, @Part MultipartBody.Part multipartFile);
}
