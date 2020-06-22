package hipravin.jda;

import hipravin.jda.graph.build.BcelClassGraphBuilder;
import hipravin.jda.graph.model.Graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class BcelSampleIT {
    String jarName = "C:\\dev\\hipravin-samples\\samples-chess\\target\\chess-0.0.1-SNAPSHOT.jar";

    @Test
    void testParseGraphChess() throws IOException {
        BcelClassGraphBuilder graphBuilder = new BcelClassGraphBuilder(jarName);
        Graph classGraph = graphBuilder.build();
    }

//    @Test
//    void testBcelJarRead() {
//        JarFile jar = null;
//        try {
//
//            jar = new JarFile(jarName);
//            Enumeration<JarEntry> entries = jar.entries();
//            while (entries.hasMoreElements()) {
//                JarEntry entry = entries.nextElement();
//                if (!entry.getName().endsWith(".class")) {
//                    continue;
//                }
//
//                ClassParser parser =
//                        new ClassParser(jarName, entry.getName());
//                JavaClass javaClass = parser.parse();
//
//                System.out.println("Class: " + javaClass.getClassName());
//                System.out.println("  Fields:");
//                for (Field field : javaClass.getFields()) {
//                    System.out.println("    " + field);
//                }
//                System.out.println("  Methods:");
//                for (Method method : javaClass.getMethods()) {
//                    System.out.println("    " + method);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (jar != null) {
//                try {
//                    jar.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}