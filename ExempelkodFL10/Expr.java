public sealed interface Expr permits Add, Constant, Divide, Multiply, Subtract {

    static double eval(Expr expr) {
        return switch (expr) {
            case Add add -> eval(add.lhs()) + eval(add.rhs());
            case Divide divide -> eval(divide.lhs()) / eval(divide.rhs());
            case Subtract subtract -> eval(subtract.lhs()) - eval(subtract.rhs());
            case Constant constant -> constant.value();
            case Multiply multiply -> eval(multiply.lhs()) * eval(multiply.rhs());
        };

    }

}


