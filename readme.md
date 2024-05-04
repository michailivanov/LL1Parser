# LL(1) Parser for English Sentences

This project implements an LL(1) parser for a subset of English sentences. The parser takes an input sentence and determines if it belongs to the defined grammar. It also provides step-by-step parsing information to visualize the parsing process.

## Grammar

The grammar for the subset of English sentences is defined as follows:

```
<S> ::= <Subject> <Verb> <Object> <Conjunction_phrase> <Dot>
<Conjunction_phrase> ::= , <Conjunction> <Subject> <Verb> <Object> <Conjunction_phrase> | ε
<Subject> ::= <Pronoun> | <Noun>
<Verb> ::= <Verb_sg> | <Verb_pl>
<Object> ::= <Noun>
<Conjunction> ::= but | or | and
<Pronoun> ::= I | you
<Noun> ::= errors | cookies | programmer | theory | cat | sleeping
<Verb_sg> ::= loves | enjoys | likes | wonders | wants
<Verb_pl> ::= love | enjoy | like | wonder | want
<Dot> ::= . | !
```

## Project Structure

The project consists of the following classes:

- `Term`: An abstract class representing a terminal or non-terminal symbol in the grammar.
- `Terminal`: A class representing a terminal symbol.
- `Nonterminal`: A class representing a non-terminal symbol.
- `Expression`: A class representing a sequence of terms.
- `CFGRule`: A class representing a context-free grammar rule.
- `LL1`: The main class implementing the LL(1) parser.
- `Main`: The entry point of the program, containing example usage and test cases.

## Usage

To use the LL(1) parser, follow these steps:

1. Create instances of the terminal and non-terminal symbols used in the grammar.
2. Define the grammar rules using the `CFGRule` class.
3. Create an instance of the `LL1` class, passing the list of grammar rules.
4. Tokenize the input sentence using the `tokenizeInput()` function.
5. Call the `parse()` method of the `LL1` instance, passing the tokenized input.
6. Handle the parsing result and any potential errors.

Example usage:

```java
// Create terminal and non-terminal symbols
Terminal I = new Terminal("I");
Terminal love = new Terminal("love");
Terminal cookies = new Terminal("cookies");
Terminal dotSymbol = new Terminal(".");

// Define grammar rules
List<CFGRule> rules = new ArrayList<>();
rules.add(new CFGRule(S, new Expression(List.of(subject, verb, object, conjunctionPhrase, dot))));
// ... add more rules

// Create LL(1) parser
LL1 ll1Parser = new LL1(rules);

// Input sentence
String inputString = "I like cookies.";

// Tokenize the input string
List<Terminal> input = tokenizeInput(inputString, terminals);

// Parse the input
Iterable<Expression> expressions = ll1Parser.parse(input);

// Print parsing steps
printParsingSteps(expressions);
```

## Error Handling

The parser handles two types of errors:

- `InvalidLL1Grammar`: Thrown when the provided grammar is not a valid LL(1) grammar.
- `LL1ParseError`: Thrown when an error occurs during the parsing process, such as encountering an unexpected terminal or missing a rule in the parsing table.

These errors are thrown as exceptions and can be caught and handled appropriately.

## Limitations

The current implementation has the following limitations:
- The grammar is limited to a specific subset of English sentences.
- The parser assumes that the input sentence is properly formatted and consists only of valid tokens defined in the grammar.
- The parser does not handle ambiguity or multiple valid parsing paths for a given sentence.