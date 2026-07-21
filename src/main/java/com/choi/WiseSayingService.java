package com.choi;
/*
역할 : 순수 비지니스 로직
스캐너 사용금지, 출력 금지
 */
public class WiseSayingService {

    // 새로운 명언 추가
    public static int tryToCreateWise(WiseSayingRepository wiseRepo, String content, String author) {
            return wiseRepo.createWiseSaying(content, author);
    }
}
