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

    // 모든 명언 반환 (id값 기준 내림차순)
    public static String getWiseList(WiseSayingRepository wiseRepo) {
        StringBuilder wiseList = new StringBuilder();
        for (int key : wiseRepo.getWiseSayingIds()) {
            wiseList.append("\n");
            wiseList.append(String.join(" / ", getWise(wiseRepo, key)));
        }
        return wiseList.toString();
    }

    // 단일 반환 [id, 작가, 명언]
    public static String[] getWise(WiseSayingRepository wiseRepo, int requestId) {
        return wiseRepo.idToWiseSaying(requestId);
    }

    // 기존 id의 명언 수정
    public static void updateWiseInRepo(WiseSayingRepository wiseRepo, int requestUpdateId, String newContent, String newAuthor) {
        wiseRepo.updateWise(wiseRepo, requestUpdateId, newContent, newAuthor);
    }

    public static int deleteWiseIfExist(WiseSayingRepository wiseRepo, int requestDeleteId) {
        return wiseRepo.deleteWise(requestDeleteId);
    }

    public static boolean isExistKey(WiseSayingRepository wiseRepo, int requestId) {
        return wiseRepo.isWiseSayingExist(requestId);
    }
}
