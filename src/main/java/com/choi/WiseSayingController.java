package com.choi;

import java.util.HashMap;
import java.util.Scanner;

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
        HashMap<String, String> params = new HashMap<>();

        if (command.length == 2) {
            params = parseParams(command[1]);
        }

        switch (cmd){
            case "등록":
                createNewReqToService();
                break;
            case "목록":
                readAllReqToService();
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
    private void readAllReqToService(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (WiseSaying w : wiseSayingService.getWiseListInRepo()){
            System.out.println("%d / %s / %s".formatted(w.getId(), w.getAuthor(), w.getContent()));
        }
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
