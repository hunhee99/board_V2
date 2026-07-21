import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("종료")
    void t1(){
        String out = AppTestRunner.run("""
                종료
                """);

        assertThat(out).contains("""
                == 명언 앱 ==
                """);
    }
}
