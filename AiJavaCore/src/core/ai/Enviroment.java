package core.ai;



import java.util.List;

public interface Enviroment<Type extends State> {
    public List<Action> getApplicableActions(Type state);
    public Type getFinalState();
    public Type getSudokuState();
    public ActionList getActionList(State state);
}
