package com.choi;
/*
역할 : 명언 객체(번호/명언내용/작가)
이 파일은 컨트롤러, 서비스, 리포지터티 모두에서 사용가능
 */
public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    // 명언
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // 작가
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // id
    public int getId() {
        return id;
    }
}
