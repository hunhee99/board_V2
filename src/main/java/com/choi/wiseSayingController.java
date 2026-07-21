package com.choi;

import java.util.Scanner;

/*
역할 : 고객의 명령을 입력받고 적절을 응답을 표현
이 단계에서는 스캐너 사용가능
이 단계에서는 출력 사용가능
 */
public class wiseSayingController {

    public static void runCommand(Scanner sc, WiseSayingRepository wiseRepo, String cmd){
        switch (cmd){
            case "등록":
                createNewWise(wiseRepo, sc);
                break;
        }
    }

    private static void createNewWise(WiseSayingRepository wiseRepo, Scanner sc){
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        int newId = WiseSayingService.tryToCreateWise(wiseRepo, content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(newId));
    }


}
