import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // args [0] - path to java file to be translated
        CharStream stream = CharStreams.fromFileName("src/main/java/ToBeTranslated.java"); //args[0]

        // Get our lexer
        Java8Lexer lexer = new Java8Lexer(stream);

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        Java8Parser parser = new Java8Parser(tokens);

        ParseTree parseTree = parser.classDeclaration();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        Java8Listener listener = new JavaToPythonListener();
        parseTreeWalker.walk(listener, parseTree);
    }
}
