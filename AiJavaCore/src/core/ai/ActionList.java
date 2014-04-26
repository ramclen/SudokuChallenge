package core.ai;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActionList {

    private List<Action> actionList;

    public ActionList() {
        this.actionList = new ArrayList<>();
    }

    public List<Action> getActions() {
        return actionList;
    }

    public boolean add(Action e) {
        return actionList.add(e);
    }

    public boolean remove(Action action) {
        return actionList.remove(action);
    }

    public boolean addAll(Collection<? extends Action> clctn) {
        return actionList.addAll(clctn);
    }

    public boolean removeAll(Collection<?> clctn) {
        return actionList.removeAll(clctn);
    }

    public Action remove(int i) {
        return actionList.remove(i);
    }
}
