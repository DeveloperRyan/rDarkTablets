package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import rDarkTablets.Constants;
import rDarkTablets.Task;

public class Mine extends Task {
    public Mine(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Players.local().animation() == -1
                && !Inventory.isFull()
                && Inventory.stream().name(Constants.DARK_ESSENCE_BLOCK).isEmpty()
                && Constants.ESSENCE_MINE_AREA.contains(Players.local().tile());
    }

    @Override
    public void execute() {
        GameObject essence = Objects.stream().name(Constants.DENSE_RUNESTONE).nearest().first();

        if (essence.inViewport()) {
            System.out.println("[DEBUG] Clicking essence mine");
            essence.finteract("Chip", Constants.DENSE_RUNESTONE);
            Condition.wait(() -> !essence.valid(), 150, 25);
        } else {
            System.out.println("[DEBUG] Rotating to see essence");
            Camera.turnTo(essence);
        }
    }
}
