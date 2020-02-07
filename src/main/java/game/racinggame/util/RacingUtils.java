package game.racinggame.util;


import org.apache.commons.lang3.StringUtils;

public class RacingUtils {

    private static final String COMMA = ",";
    private static final String WHITE_SPACE = "\\s";
    private static final String NONE = "";

    private RacingUtils(){}

    public static String[] splitLineByWhiteSpace(final String str) {
        return str.split(WHITE_SPACE);
    }

    public static String[] splitLineByComma(final String str) {

        validateString(str);

        return removeWhiteSpace(str).split(COMMA);
    }

    private static String removeWhiteSpace(final String str) {

        validateString(str);

        return str.replaceAll(WHITE_SPACE, NONE);
    }

    private static void validateString(final String str) {
        if(StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("경주할 자동차 이름에 공백 또는 null 입력되었습니다.");
        }
    }

}
