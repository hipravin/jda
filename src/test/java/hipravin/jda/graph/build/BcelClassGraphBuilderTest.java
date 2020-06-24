package hipravin.jda.graph.build;

import hipravin.jda.graph.model.ClassNameAndPackage;
import hipravin.jda.graph.model.ParsedMethodSignature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BcelClassGraphBuilderTest {

    @Test
    void testParseGenericSignature() {
        //    public <T, E extends ChessGame> Set<List<ChessGame>> superGeneric(T echessGame, Optional<List<? super Number>> troubleSu, Optional<List<? extends Number>> troubleE, double d, ThreadLocal<AtomicReference<List<King>>> trouble3) {
        String sign = "<T:Ljava/lang/Object;E:Lhipravin/samples/chess/engine/ChessGame;>(TT;Ljava/util/Optional<Ljava/util/List<-Ljava/lang/Number;>;>;Ljava/util/Optional<Ljava/util/List<+Ljava/lang/Number;>;>;DLjava/lang/ThreadLocal<Ljava/util/concurrent/atomic/AtomicReference<Ljava/util/List<Lhipravin/samples/chess/engine/model/piece/King;>;>;>;)Ljava/util/Set<Ljava/util/List<Lhipravin/samples/chess/engine/ChessGame;>;>;";

        ParsedMethodSignature parsedSign = BcelClassGraphBuilder.parseGenericMethodSignature(sign);

        assertEquals(3, parsedSign.getClassesInReturnType().size());
        assertEquals(6, parsedSign.getClassesInMethodParams().size());

        assertTrue(parsedSign.getClassesInReturnType().contains(
                new ClassNameAndPackage("ChessGame", "hipravin.samples.chess.engine")));
        assertTrue(parsedSign.getClassesInMethodParams().contains(
                new ClassNameAndPackage("King", "hipravin.samples.chess.engine.model.piece")));
    }

    @Test
    void testCommonSignature() {
        String sign = "()Lhipravin/samples/chess/api/model/ColorDto;";

        ParsedMethodSignature parsedSign = BcelClassGraphBuilder.parseGenericMethodSignature(sign);

        assertEquals(1, parsedSign.getClassesInReturnType().size());
        assertEquals(0, parsedSign.getClassesInMethodParams().size());

        assertTrue(parsedSign.getClassesInReturnType().contains(
                new ClassNameAndPackage("ColorDto", "hipravin.samples.chess.api.model")));
    }
}