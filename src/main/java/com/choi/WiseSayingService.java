package com.choi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
역할 : 순수 비지니스 로직
스캐너 사용금지, 출력 금지
 */
public class WiseSayingService {

    // 새로운 명언 추가
    public static int tryToCreateWise(WiseSayingRepository wiseRepo, String content, String author) {
            return wiseRepo.createWiseSaying(content, author);
    }

    // 모든 명언 반환 (id값 기준 내림차순)
    public static String getWiseList(WiseSayingRepository wiseRepo) {
        StringBuilder wiseList = new StringBuilder();
        for (int key : wiseRepo.getWiseSayingIds()) {
            wiseList.append("\n");
            wiseList.append(wiseRepo.idToWiseSaying(key));
        }

        return wiseList.toString();
    }
}
