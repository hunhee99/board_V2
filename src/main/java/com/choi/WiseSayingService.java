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

    // 검색어가 포함된 명언들 반환 (검색 파라미터가 없을 시 필터링 없음)
    public PageDto getWiseListInRepo(String keywordType, String keyword, int page) {
        int pageSize = 5;
        List<WiseSaying> filtered = new ArrayList<>();
        for (int id: wiseRepo.getWiseSayingIds()){
            WiseSaying wise = getWiseInRepo(id);
            if(isMatch(wise, keywordType, keyword)){
                filtered.add(wise);
            }
        }

        int totalItems = filtered.size();
        int start = (page - 1) * pageSize;
        List<WiseSaying> content = new ArrayList<>();

        if (start < totalItems){
            int end = Math.min(start + pageSize, totalItems);
            content = new ArrayList<>(filtered.subList(start, end));
        }

        return new PageDto(page, pageSize, totalItems, content);
    }

    private boolean isMatch(WiseSaying wise, String keywordType, String keyword){
        if (keyword == null || keyword.isBlank()){
            return true;
        }
        if ("content".equals(keywordType)){
            return wise.getContent().contains(keyword);
        }
        if ("author".equals(keywordType)){
            return wise.getAuthor().contains(keyword);
        }
        return true;
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
