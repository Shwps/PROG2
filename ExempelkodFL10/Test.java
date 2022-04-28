import com.sun.security.jgss.GSSUtil;

public class Test {

    public static void main(String[] args) {
        Expr expr = new Add(new Constant(10), new Multiply(new Constant(3), new Constant(3)));
        System.out.println(Expr.eval(expr));

        vad(1);

    }

    private static void vad(Object o) {
        String vad = switch (o) {
            case Integer i && i > 10 -> "stor int";
            case Integer i -> "int";
            case Double d -> "double";
            default -> "något annat";
        };
        System.out.println(vad);
    }

    private static void vad(Object o) {
        switch (o) {
            case Integer i && i > 10:
                System.out.println("en stor int");
                break;
            case Integer d:
                System.out.println("int");
                break;
            case Double d:
                System.out.println("double");
                break;
            default:
                System.out.println("något annat");
        }
    }

    private static void vad(Object o) {
        if (o instanceof Integer i && i > 10) {
            System.out.println("stor int");
        } else if(o instanceof Integer){
            System.out.println("int");
        } else if (o instanceof Double) {
            System.out.println("double");
        } else {
            System.out.println("något annat");
        }
    }
}
