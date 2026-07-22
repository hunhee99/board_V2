package com.choi;

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
    public String getWiseRepo() {
        StringBuilder wiseList = new StringBuilder();
        for (int key : wiseRepo.getWiseSayingIds()) {
            wiseList.append("\n");
            wiseList.append(String.join(" / ", getWiseInRepo(key)));
        }
        return wiseList.toString();
    }

    // 단일 반환 [id, 작가, 명언]
    public String[] getWiseInRepo(int requestId) {
        return wiseRepo.idToWiseSaying(requestId);
    }

    // 기존 id의 명언 수정
    public void updateWiseInRepo(int requestUpdateId, String newContent, String newAuthor) {
        wiseRepo.updateWiseSaying(requestUpdateId, newContent, newAuthor);
    }

    public int deleteWiseInRepo(int requestDeleteId) {
        return wiseRepo.deleteWiseSaying(requestDeleteId);
    }

    public boolean isKeyInRepo(int requestId) {
        return wiseRepo.isWiseSayingExist(requestId);
    }
}
