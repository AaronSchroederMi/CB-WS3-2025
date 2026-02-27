package my.pkg;

// Class declaration in visitor traversal and we "return" them

// sealed nobody can inherit except ...
sealed interface Statement permits IfStatement, whileStatement, Assign{ }

sealed interface Expression permits AdditionNode, SubtractionNode, DivisionNode, ValueNode  { }

//sealed interface Value permits ValueNode {}

record ValueNode(String value) implements Expression { }

record AdditionNode() implements Expression { }

record Assign(String id, Expression expression) implements Statement { }

record SubtractionNode() implements Expression{}

record DivisionNode() implements Expression {}

record IfStatement() implements Statement {}

record whileStatement() implements Statement {}
