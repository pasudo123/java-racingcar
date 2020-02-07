package game.web;

import game.service.RacingGameCreateService;
import game.service.RacingGameStartService;
import game.dto.RacingCreateDto;
import game.racinggame.RacingTracer;
import game.web.config.RacingSessionStore;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class RacingWebGame {

    private static final String NEW_LINE = "\n";
    private static final String INDEX_HTML = "index.html";
    private static final String GAME_HTML = "game.html";
    private static final String RESULT_HTML = "result.html";

    public static void main(String[] args) {

        final RacingGameCreateService racingGameCreateService = RacingGameCreateService.create();
        final RacingGameStartService racingGameStartService = RacingGameStartService.create();
        final RacingSessionStore sessionStore = new RacingSessionStore();

        port(8080);

        // start
        get("/", (request, response) -> {

            request.session(true);

            return render(new HashMap<>(), INDEX_HTML);
        });

        post("/name", (request, response) -> {

            final String carNames = request.queryParams("names");

            sessionStore.setNameAttribute(request, carNames);

            final Map<String, Object> model = new HashMap<>();

            model.put("cars", racingGameCreateService.getRacingNames(carNames));

            return render(model, GAME_HTML);
        });

        // 사용설명에서는 POST 라고 되어있는데 get 으로 받아야함. query string
        get("/result", (request, response) -> {

            final Integer round = Integer.parseInt(request.queryParams("turn"));

            sessionStore.setRoundAttribute(request, round);

            final Map<String, Object> model = new HashMap<>();

            final RacingCreateDto createDto = RacingCreateDto.createByWeb(
                    sessionStore.getNameAttribute(request),
                    sessionStore.getRoundAttribute(request)
            );

            final RacingTracer tracer = racingGameStartService.startGame(createDto);

            model.put("traces", tracer.getTraces().split(NEW_LINE));
            model.put("winners", tracer.createRacingTracerResultDevice().getRacingWinners());

            sessionStore.clearSession(request);

            return render(model, RESULT_HTML);
        });
    }

    static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
