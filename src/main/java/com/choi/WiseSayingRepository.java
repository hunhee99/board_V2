package com.choi;

import java.util.*;

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

    // 전체 키 값 내림차순 반환 (TreeSet으로 내림차순 정렬 후 반환)
    public Set<Integer>  getWiseSayingIds() {
        Set<Integer> wiseSayingIds = new TreeSet<>(Comparator.reverseOrder());
        wiseSayingIds.addAll(wiseSayingMap.keySet());
        return wiseSayingIds;
    }

    // 명언 반환
    // [id, 작가, 명언] 형식으로 반환
    public String[] idToWiseSaying(int wiseSayingId) {
        String id = String.valueOf(wiseSayingMap.get(wiseSayingId).getId());
        String content = wiseSayingMap.get(wiseSayingId).getContent();
        String author = wiseSayingMap.get(wiseSayingId).getAuthor();
        return new String[]{id, author, content};
    }

    // id에 맞는 명언이 존재하는지 반환
    public boolean isWiseSayingExist(int key){
        return wiseSayingMap.containsKey(key);
    }

    // id에 해당하는 명언 삭제 -> 삭제한 명언의 id 반환
    public int deleteWiseSaying(int requestDeleteId) {
        return wiseSayingMap.remove(requestDeleteId).getId();
    }

    // 특정 id의 명언 수정
    public void updateWiseSaying(int requestUpdateId, String newContent, String newAuthor) {
        WiseSaying target = wiseSayingMap.get(requestUpdateId);
        target.setContent(newContent);
        target.setAuthor(newAuthor);
    }
}
