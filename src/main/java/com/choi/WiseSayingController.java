package com.choi;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static javax.swing.UIManager.put;

/*
역할 : 고객의 명령을 입력받고 적절을 응답을 표현
이 단계에서는 스캐너 사용가능
이 단계에서는 출력 사용가능
 */
public class WiseSayingController {
    private final Scanner sc;
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner sc, WiseSayingService wiseSayingService){
        this.sc = sc;
        this.wiseSayingService = wiseSayingService;
    }


    public void runCommand(String query){
        String[] command = query.split("\\?");
        String cmd = command[0];
        HashMap<String, String> params = new HashMap<>() {{
            put("keywordType", "");
            put("keyword", "");
            put("page", "1");
        }};

        if (command.length == 2) {
            params.putAll(parseParams(command[1]));
        }

        switch (cmd){
            case "등록":
                createNewReqToService();
                break;
            case "목록":
                String keywordType = params.get("keywordType");
                String keyword = params.get("keyword");
                Integer page = parseId(params.get("page"));

                readAllReqToService(keywordType, keyword, page);
                break;
            case "삭제":
                Integer deleteId = parseId(params.get("id"));
                if (deleteId == null){
                    break;
                }
                deleteReqToService(deleteId);
                break;
            case "수정":
                Integer updateId = parseId(params.get("id"));
                if (updateId == null){
                    break;
                }
                updateReqToService(updateId);
                break;
        }
    }

    // 등록 요청
    private void createNewReqToService(){
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        int newId = wiseSayingService.createWiseToRepo(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(newId));
    }

    // 목록 요청
    private void readAllReqToService(String keywordType, String keyword, int page){
        PageDto pageDto = wiseSayingService.getWiseListInRepo(keywordType, keyword, page);
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (WiseSaying wiseSaying : pageDto.getContent()) {
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
        printPageMenu(pageDto);
    }
    private void printPageMenu(PageDto pageDto) {
        String menu = IntStream.rangeClosed(1, pageDto.getTotalPages())
                .mapToObj(i -> i == pageDto.getPage() ? "[%d]".formatted(i) : String.valueOf(i))
                .collect(Collectors.joining(" / "));

        System.out.println("페이지 : " + menu);
    }

    // 삭제 요청
    private void deleteReqToService(int requestDeleteId){
        int deletedId = wiseSayingService.deleteWiseInRepo(requestDeleteId);
        if (deletedId == -1){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(requestDeleteId));
            return;
        }
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(deletedId));
    }

    // 수정 요청
    private void updateReqToService(int requestUpdateId) {
        // 존재하는지 확인
        WiseSaying origin = wiseSayingService.getWiseInRepo(requestUpdateId);

        if (origin == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(requestUpdateId));
            return;
        }

        System.out.println("명언(기존) : %s".formatted(origin.getContent()));
        System.out.print("명언 : ");
        String newContent = sc.nextLine();

        System.out.println("작가(기존) : %s".formatted(origin.getAuthor()));
        System.out.print("작가 : ");
        String newAuthor = sc.nextLine();

        wiseSayingService.updateWiseInRepo(requestUpdateId, newContent, newAuthor);
    }


    // 파라미터 파싱
    private HashMap<String, String> parseParams(String rawParams){
        String[] params = rawParams.split("&");
        HashMap<String, String> parsedParams = new HashMap<>();

        for (String param : params) {
            String[] keyValue = param.split("=");
            parsedParams.put(keyValue[0], keyValue[1]);
        }

        return parsedParams;
    }

    // id 파싱
    private Integer parseId(String rawId){
        if (rawId == null){ return null; }
        try {
            return Integer.parseInt(rawId);
        }
        catch (NumberFormatException e){
            return null;
        }
    }
}
