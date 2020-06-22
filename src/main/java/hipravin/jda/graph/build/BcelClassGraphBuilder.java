package hipravin.jda.graph.build;

import hipravin.jda.graph.model.ClassNameAndPackage;
import hipravin.jda.graph.model.Graph;
import hipravin.jda.graph.model.ParsedMethodSignature;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BcelClassGraphBuilder {

    private final String jarFileName;
    private final Collection<Predicate<? super JarEntry>> excludeEntriesPredicates =
            Arrays.asList(nonClassEntries());

    public BcelClassGraphBuilder(String jarFileName) {
        this.jarFileName = jarFileName;
    }

    public Graph build() throws IOException {

        try (JarFile jar = new JarFile(jarFileName)) {
            Collections.list(jar.entries()).stream()
                    .filter(e -> excludeEntriesPredicates.stream().noneMatch(p -> p.test(e)))
                    .forEach(this::processJarEntry);
        }

        return null;
    }

    private ParsedJavaClass parseJarClassEntry(JarEntry entry) {
        ClassParser parser = new ClassParser(jarFileName, entry.getName());
        try {
            JavaClass javaClass = parser.parse();

            String className = javaClass.getClassName();
            String packageName = javaClass.getPackageName();



            System.out.println("Class: " + javaClass.getClassName());
            System.out.println("  Fields:");
            for (Field field : javaClass.getFields()) {
                System.out.println("    " + field);
            }
            System.out.println("  Methods:");
            for (Method method : javaClass.getMethods()) {
                System.out.println("    " + method);
            }

            return new ParsedJavaClass(className, packageName);

        } catch (IOException e) {
            throw new GraphBuildEception(e);
        }

    }

    private void processJarEntry(JarEntry entry) {
        ParsedJavaClass parsedJavaClass = parseJarClassEntry(entry);



    }

    private Optional<ParsedMethodSignature> parseMethod(Method method) {
        return method.getGenericSignature() != null
                ? parseGenericMethodSignature(method.getGenericSignature())
                : Optional.of(parseCommonMethodSignature(method.getSignature()));

    }

    protected static ParsedMethodSignature parseCommonMethodSignature(String genericSignature) {
        //e.g. ()Lhipravin/samples/chess/api/model/ColorDto;
        return null;
    }

    private static final Pattern SIGN_PATTERN = Pattern.compile(".*\\((.*)\\)(.*)");
    private static final Pattern SIGN_CLASS_PATERN = Pattern.compile("L([^;<]+)");

    //    public <T, E extends ChessGame> Set<List<ChessGame>> superGeneric(T echessGame, Optional<List<? super Number>> troubleSu, Optional<List<? extends Number>> troubleE, double d, ThreadLocal<AtomicReference<List<King>>> trouble3) {
    //   <T:Ljava/lang/Object;E:Lhipravin/samples/chess/engine/ChessGame;>(TT;Ljava/util/Optional<Ljava/util/List<-Ljava/lang/Number;>;>;Ljava/util/Optional<Ljava/util/List<+Ljava/lang/Number;>;>;DLjava/lang/ThreadLocal<Ljava/util/concurrent/atomic/AtomicReference<Ljava/util/List<Lhipravin/samples/chess/engine/model/piece/King;>;>;>;)Ljava/util/Set<Ljava/util/List<Lhipravin/samples/chess/engine/ChessGame;>;>;
    protected static Optional<ParsedMethodSignature> parseGenericMethodSignature(String signature) {
        //e.g.  (Ljava/util/List<Lhipravin/samples/chess/api/model/PieceDto;>;)V
        Matcher sm = SIGN_PATTERN.matcher(signature);
        if(sm.find()) {
            String params = sm.group(1);
            String retVal = sm.group(2);

            Set<ClassNameAndPackage> classesInParams = findClassesInGenericSignature(params)
                    .stream()
                    .map(ClassNameAndPackage::fromSlashed)
                    .collect(Collectors.toSet());

            Set<ClassNameAndPackage> classesInReturnType = findClassesInGenericSignature(retVal)
                    .stream()
                    .map(ClassNameAndPackage::fromSlashed)
                    .collect(Collectors.toSet());

            return Optional.of(new ParsedMethodSignature(classesInReturnType, classesInParams));
        }

        return Optional.empty();
    }

    private static List<String> findClassesInGenericSignature(String signPart) {
        List<String> classes = new ArrayList<>();
        Matcher m = SIGN_CLASS_PATERN.matcher(signPart);

        while(m.find()) {
            classes.add(m.group(1));
        }

        return classes;
    }


    private static Predicate<? super JarEntry> nonClassEntries() {
        return (Predicate<JarEntry>) jarEntry -> !jarEntry.getName().toLowerCase().endsWith(".class");
    }
}
