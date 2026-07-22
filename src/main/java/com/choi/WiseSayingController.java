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
                int deleteId = Integer.parseInt(params.get("id"));
                deleteReqToSerivce(deleteId);
                break;
            case "수정":
                int updateId = Integer.parseInt(params.get("id"));
                updateReqToService(updateId);
                break;
        }
    }

    // 등록
    private void createNewReqToService(){
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        int newId = wiseSayingService.createWiseToRepo(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(newId));
    }

    // 목록
    private void readAllReqToService(){
        String output = wiseSayingService.getWiseRepo();
        System.out.print("""
                번호 / 작가 / 명언
                ----------------------""");
        System.out.println(output);
    }

    // 삭제
    private void deleteReqToSerivce(int requestDeleteId){
        if (!wiseSayingService.isKeyInRepo(requestDeleteId)) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(requestDeleteId));
            return;
        }

        int deletedId = wiseSayingService.deleteWiseInRepo(requestDeleteId);
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(deletedId));
    }

    // 수정
    private void updateReqToService(int requestUpdateId) {
        if (!wiseSayingService.isKeyInRepo(requestUpdateId)) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(requestUpdateId));
            return;
        }
        String[] origin = wiseSayingService.getWiseInRepo(requestUpdateId);
        String newContent;
        String newAuthor;

        System.out.println("명언(기존) : %s".formatted(origin[2]));
        System.out.print("명언 : ");
        newContent = sc.nextLine();

        System.out.println("작가(기존) : %s".formatted(origin[1]));
        System.out.print("작가 : ");
        newAuthor = sc.nextLine();

        wiseSayingService.updateWiseInRepo(requestUpdateId, newContent, newAuthor);
    }


    // 파라미터 파싱
    private HashMap<String, String> parseParams(String rawParams){
        String[] params = rawParams.split("&amp;");
        HashMap<String, String> parsedParams = new HashMap<>();

        for (String param : params) {
            String[] keyValue = param.split("=");
            parsedParams.put(keyValue[0], keyValue[1]);
        }

        return parsedParams;
    }
}
