package var;

public record Var(String var) {
    public static Var var(String var) {
        return new Var(var);
    }
}
