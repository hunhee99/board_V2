package util;

import com.choi.util.Util;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


public class UtilFileTest {
    // @BeforeAll, @AfterAll => 전체 테스트 전후 처리
    // @BeforeEach, @AfterEach => 각 테스트 전후 처리

    @BeforeAll
    static void beforeAll() {
        Util.file.mkdir("temp");
    }

    @AfterAll
    static void afterAll() {
        Util.file.rmdir("temp");
    }




    @Test
    @DisplayName("파일 생성")
    void t1() {

        // 무언가를 세팅하고
        String filePath = "temp/test.txt";

        // 수행하면
        Util.file.touch(filePath);

        // 결과가 나온다. => 실제 파일이 존재하는가?
        boolean rst = Util.file.exists(filePath);
        Util.file.delete(filePath);
        assertThat(rst).isTrue();

    }
    @Test
    @DisplayName("파일 삭제")
    void t2() {

        // given
        String filePath = "temp/test.txt";
        Util.file.touch(filePath); // 파일 생성

        // when
        Util.file.delete(filePath);

        // then
        boolean rst = Util.file.exists(filePath);
        assertThat(rst).isFalse();

    }


    @Test
    @DisplayName("파일 읽기/쓰기")
    void t3() {

        // given
        String filePath = "temp/test.txt";
        Util.file.set(filePath, "hello world"); // 파일 쓰기

        // when
        String content = Util.file.get(filePath, "");

        // then
        assertThat(content).isEqualTo("hello world");
        Util.file.delete(filePath);
    }

    @Test
    @DisplayName("파일 생성 - 경로에 폴더가 없는 경우")
    void t4() {

        // given
        String filePath = "temp/temp/test.txt";

        // when
        Util.file.touch(filePath); // 파일 생성

        // then
        boolean rst = Util.file.exists(filePath);
        assertThat(rst).isTrue();

        // 테스트가 끝나면 파일 삭제
        Util.file.delete(filePath);
    }
}