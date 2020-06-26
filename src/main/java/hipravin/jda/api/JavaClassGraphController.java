package hipravin.jda.api;

import hipravin.jda.api.model.GraphDto;
import hipravin.jda.graph.build.GraphBuildEception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class JavaClassGraphController {
    private final ClassGraphService classGraphService;

    public JavaClassGraphController(ClassGraphService classGraphService) {
        this.classGraphService = classGraphService;
    }

    @GetMapping("/sample")
    public GraphDto sampleChessRandom() throws IOException {
        return classGraphService.buildGraph("C:\\dev\\hipravin-samples\\jda\\src\\test\\resources\\chess-sample.jar");
    }

    @ExceptionHandler(GraphBuildEception.class)
    public ResponseEntity<?> handleNotFoind(GraphBuildEception e) {
        return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
