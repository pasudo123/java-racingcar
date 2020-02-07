package game.web.config;

import spark.Request;

public class RacingSessionStore {

    private static final String NAME = "name";
    private static final String ROUND = "round";

    // 동일한 작업에 대한 수행을 막아야 한다... (디자인 패턴 고려)

    public void setNameAttribute(final Request request, final String value){

        this.validateRequest(request);
        this.validateValue(value);

        request.session().attribute(NAME, value);
    }

    public String getNameAttribute(final Request request) {

        this.validateRequest(request);

        return request.session().attribute(NAME);
    }

    public void setRoundAttribute(final Request request, final Integer value) {

        this.validateRequest(request);
        this.validateValue(value);

        request.session().attribute(ROUND, value);
    }

    public Integer getRoundAttribute(final Request request) {

        this.validateRequest(request);

        return request.session().attribute(ROUND);
    }

    public void clearSession(final Request request) {
        request.session().removeAttribute(NAME);
        request.session().removeAttribute(ROUND);
    }

    private void validateRequest(final Request request){
        if(request == null) {
            throw new IllegalArgumentException("요청 값이 널이기 때문에 세션을 획득할 수 없습니다.");
        }
    }

    private void validateValue(final Object value) {
        if(value == null) {
            throw new IllegalArgumentException("세팅할 어트리뷰트 값이 널이기 때문에 세션을 세팅하지 못합니다.");
        }
    }
}
