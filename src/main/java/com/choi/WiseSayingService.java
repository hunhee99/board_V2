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

    // 모든 명언 반환 (id값 기준 내림차순)
    public List<WiseSaying> getWiseListInRepo() {
        List<WiseSaying> list = new ArrayList<>();
        for (int key : wiseRepo.getWiseSayingIds()) {
            list.add(wiseRepo.findWiseSayingById(key));
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
