package game.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class RacingWebUtils {

    private static final String SPACE = "\\s";

    public static List<String> splitLineBySpace(final String str) {

        validateString(str);

        return Arrays.asList(str.split(SPACE));
    }

    private static void validateString(final String str) {
        if(StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("경주할 자동차 이름에 공백 또는 null 입력되었습니다.");
        }
    }
}
