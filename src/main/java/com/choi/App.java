package com.choi;

import java.util.Scanner;
/*
역할 : 사용자 입력을 받고 그것이 WiseSayingController 에게 넘거야 하는지 판단해서 맞으면 넘김(넘김의 의미 : 메서드 호출(인자와 함께))
이 단계에서는 스캐너 사용가능
이 단계에서는 출력 사용가능
 */
public class App {
    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }


    public void run(){
        WiseSayingRepository wiseRepo = new WiseSayingRepository();
        System.out.println("== 명언 앱 ==");

        String cmd = "";

        while(!cmd.equals("종료")){
            System.out.print("명령) ");
            cmd = sc.nextLine();
            wiseSayingController.runCommand(sc, wiseRepo, cmd);
        }

        return;
    }
}
