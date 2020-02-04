package game.web;

import game.domain.RacingGameCreateService;
import game.domain.RacingGameStartService;
import game.dto.RacingWebCreateDto;
import game.racinggame.RacingTracer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static spark.Spark.*;

public class RacingWebGame {

    private static final String SPACE = " ";
    private static final String INDEX_HTML = "index.html";
    private static final String GAME_HTML = "game.html";
    private static final String RESULT_HTML = "result.html";

    public static void main(String[]args) {

        final RacingGameCreateService racingGameCreateService = RacingGameCreateService.create();
        final RacingGameStartService racingGameStartService = RacingGameStartService.create();

        port(8080);

        // start
        get("/", (request, response) ->
                render(new HashMap<>(), INDEX_HTML)
        );

        AtomicReference<List<String>> carNames = new AtomicReference<>();

        post("/name", (request, response) -> {

            final Map<String, Object> model = new HashMap<>();

            carNames.set(racingGameCreateService.getRacingNames(request.queryParams("names")));

            model.put("cars", carNames.get());

            return render(model, GAME_HTML);
        });

        // 사용설명에서는 POST 라고 되어있는데 get 으로 받아야함. query string
        get("/result", (request, response) -> {

            final Map<String, Object> model = new HashMap<>();

            final RacingWebCreateDto dto = new RacingWebCreateDto(carNames.get(), Integer.parseInt(request.queryParams("turn")));
            final RacingTracer tracer = racingGameStartService.startGame(dto);

            model.put("traces", tracer.getTraces().split("\n"));
            model.put("winners", tracer.createRacingTracerResultDevice().getRacingWinners());

            return render(model, RESULT_HTML);
        });
    }

    static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
