package core.ai;



public interface Action<Type extends State> {

    public abstract Type execute(Type state);

    public abstract boolean isApplicable(Type state);
    
}
