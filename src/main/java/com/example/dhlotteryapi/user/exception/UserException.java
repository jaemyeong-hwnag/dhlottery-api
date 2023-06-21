package com.example.dhlotteryapi.user.exception;

public class UserException extends RuntimeException{
    /**
     * 인증 익셉션
     * <p>
     * todo 로그인 실패에 따른 정보 노출
     * @param e
     */
    public UserException(final Exception e) {
        super("로그인 실패", e);
    }
}
