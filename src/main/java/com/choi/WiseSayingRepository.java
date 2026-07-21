package com.choi;

import java.util.HashMap;
import java.util.LinkedList;

/*
역할 : 데이터의 조회/수정/삭제/생성을 담당
스캐너 사용금지, 출력 금지
 */
public class WiseSayingRepository {
    private HashMap<Integer, WiseSaying> wiseSayingMap = new HashMap<>();
    private int maxIdNum = 0;

    // 명언 생성
    public int createWiseSaying(String content, String author){
        WiseSaying newWiseSaying = new WiseSaying(++maxIdNum, content, author);
        int newId = newWiseSaying.getId();
        wiseSayingMap.put(newId, newWiseSaying);
        return newId;
    }

}
