# Java to Python Translator

## Table of Contents
1. [Description](#description)
2. [Technologies](#technologies)
3. [Definition of grammar, lexer and parser generating](#definition-of-grammar-lexer-and-parser-generating)
4. [Functional testing of lexer and parser](#functional-testing-of-lexer-and-parser)
- [Setting up](#setting-up)
- [Lexer](#lexer)
- [Parser](#parser)
6. [Description of translator functionality](#description-of-translator-functionality)
- [Run example](#run-example)
7. [Documentation](#documentation)

## Description
The project puropse is to implement translator from subset of Java language to Python. During implementation it is essential to use specific lexer and parser generator (depending on selected programming language).

## Technologies
- **Java** - Translator implementation
- **ANTLR** - Lexer and parser generator

ANTLR is attached using Maven plugin - it is the easiest way to set a project using ANTLR, without downloading and installing .jar library.
 
## Definition of grammar, lexer and parser generating
**Grammar:** Is is used pre-prepared grammar file by ANTLR - [java8.g4](https://github.com/antlr/grammars-v4/tree/master/java/java8).

`mvn package` - generating Java code corresponding to the given grammar file (file names are identical to the grammar file):

<img width="150" alt="Screenshot 2021-08-15 at 13 28 31" src="https://user-images.githubusercontent.com/34041060/129477105-caf1efd2-3b4b-4453-8dde-17374259b51e.png">

- `Java8Lexer.java` – includes lexer implementation. There are defined rules which transform input words to tokens.
- `Java8Paraser.java` – includes parser implementation. There are defined rules which trasnform tokens to syntactic tree.
- `Java8Listener.java, Java8BaseListener` – these files describe interface and class which allow to traverse within syntactic.
 
## Functional testing of Lexer and Parser
Functional testing of Lexer and Parser is possible using TestRig (*org.antlr.v4.gui.TestRig*). To use this tool, ANTLR installation is essential.

### Setting up
1. Downloading ANTLR library: [antlr-4.9.2-complete.jar](https://www.antlr.org/download/antlr-4.9.2-complete.jar).
2. Adding classes from downloaded file to CLASSPATH.
3. Creating aliases which simplify the commands syntax - `.bash_profile` / `.zshrc` file:
```
# ANTLR 4
export ANTLR_HOME="/Users/igordzierwa/Desktop/java-to-python-translator-master/src/main/java/ANTLR"
export ANTLR_JAR="$ANTLR_HOME/antlr-4.9.2-complete.jar" 
export CLASSPATH=".:$ANTLR_JAR:$CLASSPATH"
alias antlr4="java -jar $ANTLR_JAR"
alias grun="java org.antlr.v4.gui.TestRig"
```
4. Compilation all .java classes:  
`javac Java8*.java`

### Lexer
The command takes the following arguments:
- Grammar name / implemented Lexer package: *Java8*
- Rule name: *java8*
- Generated result option: *tokens*
- Path to the test file: *ToBeTranslated.java*

`grun Java8 java8 -tokens ToBeTranslated.java`


**Result fragment:**

<img width="200" alt="Screenshot 2021-08-15 at 13 28 31" src="https://user-images.githubusercontent.com/34041060/129479784-88726368-f9ba-4fb6-9150-c612de53920d.png">

**Token structure:**
1. Token number
2. Starting and ending character number of the token value
3. Token value
4. Token type (grammar rule)
5. Token position (row number and starting character number in this row)

<img width="400" alt="Screenshot 2021-08-15 at 13 28 31" src="https://user-images.githubusercontent.com/34041060/129480440-24a985b6-0f53-4d36-aa44-a797597ccfe5.png">

### Parser
The command takes the following arguments:
- Grammar name / implemented Lexer package: *Java8*
- Rule name: *java8*
- Generated result option: *tree*
- Path to the test file: *ToBeTranslated.java*
- Flag: *gui* (syntactic tree visualisation)

`grun Java8 methodDeclaration -tree ToBeTranslated.java -gui`

**Command result:**

<img width="1030" alt="Screen Shot 2021-08-15 at 15 48 24 PM" src="https://user-images.githubusercontent.com/34041060/129480738-4419946c-de09-4a1d-845b-5a93765413af.png">

## Description of translator functionality
Traversing within syntactic tree is based on the listeners mechanism which reacts to primary events like: entries and exits from a defined node type.

### Implemented Listeners in JavaToPythonListener class:**
- Method declaration
- Method arguments declaration
- Variables declaration
- Class declaration
- Loop definition and preincrementation/posincrementation
- Print method definition
- Conditional statement definition
- Return instruction definition

### Run example
**Input file:** *ToBeTranslated.java*
```java
public class ToBeTranslated {
    void arithmeticOperation(int a, double d, double b){
        double c = a + b + d;
        System.out.println(c);
        for (int i = 0; i < 3; i++){
            System.out.println(i);
        }
        System.out.print(d);
    }

    double returnValue(int a, int b, String value) {
        System.out.println(value);
        int c = a + b;
        return c;
    }

    void newMethod(int x, int y){
        if(x==y){
            System.out.println("statement is true");
        }


        if (x!=y){
            System.out.println("inside is statement");
        } else {
            System.out.print("inside else statement");
        }
    }
}
```

**Result:**

<img width="1000" alt="Screen Shot 2021-08-15 at 17 34 49 PM" src="https://user-images.githubusercontent.com/34041060/129483921-ddb7ac2d-e893-412e-99c0-3a6d7b83d2f5.png">

## Documentation
[Translator documentation (in Polish)](https://github.com/igordzie97/java-to-python-translator/blob/main/documentation/documentation.pdf)

## Authors
- Igor Dzierwa
- Adrian Nędza
