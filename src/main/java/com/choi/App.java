package com.choi;

import java.util.Scanner;

public class App {
    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }


    public void run(){
        System.out.println("== 명언 앱 ==");

        String cmd = "";

        while(!cmd.equals("종료")){
            cmd = sc.nextLine();
        }
    }
}
