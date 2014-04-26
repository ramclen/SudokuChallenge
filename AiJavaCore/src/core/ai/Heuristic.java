package core.ai;



public interface Heuristic<Type extends State>{

    public double evaluate(Type state);
    
}
