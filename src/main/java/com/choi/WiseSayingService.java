package com.choi;


import java.util.ArrayList;
import java.util.List;

/*
역할 : 순수 비지니스 로직
스캐너 사용금지, 출력 금지
 */
public class WiseSayingService {

    private final WiseSayingRepository wiseRepo;

    public WiseSayingService(WiseSayingRepository wiseRepo) {
        this.wiseRepo = wiseRepo;
    }

    // 새로운 명언 추가
    public int createWiseToRepo(String content, String author) {
            return wiseRepo.createWiseSaying(content, author);
    }

    // 검색어가 포함된 명언들 반환
    public List<WiseSaying> getWiseListInRepo(String keywordType, String keyword) {
        List<WiseSaying> list = new ArrayList<>();


        for (int key : wiseRepo.getWiseSayingIds()) {
            WiseSaying wise = wiseRepo.findWiseSayingById(key);
            if (keywordType.equals("content")) {
                if (wise.getContent().contains(keyword)) {
                    list.add(wise);
                }
            }
            else if (keywordType.equals("author")) {
                if (wise.getAuthor().contains(keyword)) {
                    list.add(wise);
                }
            }
            else {
                list.add(wise);
            }
        }
        return list;
    }

    // 단일 반환 [id, 작가, 명언]
    public WiseSaying getWiseInRepo(int requestId) {
        return wiseRepo.findWiseSayingById(requestId);
    }

    // 기존 id의 명언 수정
    public void updateWiseInRepo(int requestUpdateId, String newContent, String newAuthor) {
        wiseRepo.updateWiseSaying(requestUpdateId, newContent, newAuthor);
    }

    // [삭제]
    public int deleteWiseInRepo(int requestDeleteId) {
        if (!wiseRepo.isWiseSayingExist(requestDeleteId)) {
            return -1;
        }
        return wiseRepo.deleteWiseSaying(requestDeleteId);
    }

}
