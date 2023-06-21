package com.example.dhlotteryapi.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum DHLotteryURI {
    DEFAULT_SESSION_URL("https://dhlottery.co.kr/gameResult.do?method=byWin&wiselog=H_C_1_1"),
    SYSTEM_UNDER_CHECK_URL("https://dhlottery.co.kr/index_check.html"),
    MAIN_URL("https://dhlottery.co.kr/common.d?method=main"),
    LOGIN_REQUEST_URL("https://www.dhlottery.co.kr/userSsl.do?method=login"),
    BUY_LOTTO645_URL("https://ol.dhlottery.co.kr/olotto/game/execBuy.do"),
    ROUND_INFO_URL("https://www.dhlottery.co.kr/common.do?method=main"),
    ;

    private final String url;
}
