package hipravin.jda.api;

import hipravin.jda.api.model.GraphDto;
import hipravin.jda.api.model.LinkDto;
import hipravin.jda.api.model.NodeDto;
import hipravin.jda.api.model.PositionDto;
import hipravin.jda.graph.build.BcelClassGraphBuilder;
import hipravin.jda.graph.model.Graph;
import hipravin.jda.graph.model.GraphNode;
import hipravin.jda.graph.model.JavaClassType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClassGraphService {
    private final Random random = new Random();
    private static final double POSITION_MIN = -100.0;
    private static final double POSITION_MAX = 100.0;

    public GraphDto buildGraph(String localJarName) throws IOException {
        BcelClassGraphBuilder graphBuilder = new BcelClassGraphBuilder(localJarName);
        Graph classGraph = graphBuilder.build();

        GraphDto graphDto = new GraphDto();
        Map<String, PositionDto> randomPositions = classGraph.getNodes()
                .stream()
                .collect(Collectors.toMap(n -> n.getMetadata().getUniqueId(), n -> defaultRandomPosition()));


        graphDto.setNodes(classGraph.getNodes().stream()
                .map(gn -> fromGraphNode(gn, randomPositions))
                .collect(Collectors.toList()));

        return graphDto;
    }

    private NodeDto fromGraphNode(GraphNode graphNode, Map<String, PositionDto> nodePositions) {
        NodeDto nodeDto = new NodeDto();
        nodeDto.setHeader(graphNode.getMetadata().getUniqueId());
        nodeDto.setValue(graphNode.getMetadata().getMetaValue().getLongValue());
        nodeDto.setPosition(nodePositions.get(graphNode.getMetadata().getUniqueId()));
        nodeDto.setNonProjectClass(graphNode.getMetadata().getJavaClassType() == JavaClassType.NON_PROJECT);

        nodeDto.setLinks(graphNode.getLinks().stream()
                .map(gl -> {
                    return new LinkDto(nodePositions.get(gl.getTo().getMetadata().getUniqueId()),
                            gl.getMetadata().getMetaValue().getLongValue(),
                            gl.getMetadata().getUniqueId(),
                            gl.getTo().getMetadata().getUniqueId(),
                            gl.getTo().getMetadata().getJavaClassType() == JavaClassType.NON_PROJECT);
                })
                .collect(Collectors.toList()));
        return nodeDto;
    }

    private double random(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    private PositionDto randomPosition(double minX, double maxX, double minY, double maxY) {
        return new PositionDto(random(minX, maxX), random(minY, maxY));
    }

    private PositionDto defaultRandomPosition() {
        return new PositionDto(random(POSITION_MIN, POSITION_MAX), random(POSITION_MIN, POSITION_MAX));
    }

    private List<PositionDto> randomPositions(int size, double minX, double maxX, double minY, double maxY) {
        return Stream.generate(() -> randomPosition(minX, maxX, minY, maxY)).limit(size)
                .collect(Collectors.toList());
    }

}
