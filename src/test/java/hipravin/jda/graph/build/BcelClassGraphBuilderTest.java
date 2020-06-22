package hipravin.jda.graph.build;

import hipravin.jda.graph.model.ParsedMethodSignature;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BcelClassGraphBuilderTest {


    //    public <T, E extends ChessGame> Set<List<ChessGame>> superGeneric(T echessGame, Optional<List<? super Number>> troubleSu, Optional<List<? extends Number>> troubleE, double d, ThreadLocal<AtomicReference<List<King>>> trouble3) {
    //

    @Test
    void testParseGenericSignature() {
        String sign = "<T:Ljava/lang/Object;E:Lhipravin/samples/chess/engine/ChessGame;>(TT;Ljava/util/Optional<Ljava/util/List<-Ljava/lang/Number;>;>;Ljava/util/Optional<Ljava/util/List<+Ljava/lang/Number;>;>;DLjava/lang/ThreadLocal<Ljava/util/concurrent/atomic/AtomicReference<Ljava/util/List<Lhipravin/samples/chess/engine/model/piece/King;>;>;>;)Ljava/util/Set<Ljava/util/List<Lhipravin/samples/chess/engine/ChessGame;>;>;";

        Optional<ParsedMethodSignature> parsedSign = BcelClassGraphBuilder.parseGenericMethodSignature(sign);
        assertTrue(parsedSign.isPresent());




    }
}