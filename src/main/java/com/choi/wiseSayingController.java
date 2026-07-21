package com.choi;

import java.util.HashMap;
import java.util.Scanner;

/*
역할 : 고객의 명령을 입력받고 적절을 응답을 표현
이 단계에서는 스캐너 사용가능
이 단계에서는 출력 사용가능
 */
public class wiseSayingController {

    public static void runCommand(Scanner sc, WiseSayingRepository wiseRepo, String query){
        String[] command = query.split("\\?");
        String cmd = command[0];
        HashMap<String, String> params = new HashMap<>();
        if (command.length == 2) {
            params = parseParams(command[1]);
        }

        switch (cmd){
            case "등록":
                createNewWise(wiseRepo, sc);
                break;
            case "목록":
                readWiseList(wiseRepo);
                break;
            case "삭제":
                int deleteId = Integer.parseInt(params.get("id"));
                deleteWise(wiseRepo, deleteId);
                break;
            case "수정":
                int updateId = Integer.parseInt(params.get("id"));
                updateId(wiseRepo, sc, updateId);
                break;
        }
    }

    // 등록
    private static void createNewWise(WiseSayingRepository wiseRepo, Scanner sc){
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        int newId = WiseSayingService.tryToCreateWise(wiseRepo, content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(newId));
    }

    // 목록
    private static void readWiseList(WiseSayingRepository wiseRepo){
        String output = WiseSayingService.getWiseList(wiseRepo);

        System.out.print("""
                번호 / 작가 / 명언
                ----------------------""");
        System.out.println(output);
    }

    // 삭제
    private static void deleteWise(WiseSayingRepository wiseRepo, int requestDeleteId){
        if (!WiseSayingService.isExistKey(wiseRepo, requestDeleteId)) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(requestDeleteId));
            return;
        }

        int deletedId = WiseSayingService.deleteWiseIfExist(wiseRepo, requestDeleteId);
        System.out.println("%d번 명언이 삭제되었습니다.".formatted(deletedId));
    }

    // 수정
    private static void updateId(WiseSayingRepository wiseRepo, Scanner sc, int requestUpdateId) {
        if (!WiseSayingService.isExistKey(wiseRepo, requestUpdateId)) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(requestUpdateId));
            return;
        }
        String[] origin = WiseSayingService.getWise(wiseRepo, requestUpdateId);
        String newContent;
        String newAuthor;

        System.out.println("명언(기존) : %s".formatted(origin[2]));
        System.out.print("명언 : ");
        newContent = sc.nextLine();

        System.out.println("작가(기존) : %s".formatted(origin[1]));
        System.out.print("작가 : ");
        newAuthor = sc.nextLine();

        WiseSayingService.updateWiseInRepo(wiseRepo, requestUpdateId, newContent, newAuthor);
    }


    // 파라미터 파싱
    private static HashMap<String, String> parseParams(String rawParams){
        String[] params = rawParams.split("&amp;");
        HashMap<String, String> parsedParams = new HashMap<>();

        for (String param : params) {
            String[] keyValue = param.split("=");
            parsedParams.put(keyValue[0], keyValue[1]);
        }

        return parsedParams;
    }
}
