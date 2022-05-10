package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
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
                && Inventory.stream().filter(i -> i.name().equals("Dark essence block")).isEmpty()
                && Constants.essenceMineArea.contains(Players.local().tile());
    }

    @Override
    public void execute() {
        GameObject essence = Objects.stream().name("Dense runestone").nearest().first();

        if (essence.inViewport()) {
            System.out.println("[DEBUG] Clicking essence mine");
            essence.finteract("Chip", "Dense runestone");
            Condition.wait(() -> Objects.stream().at(essence.tile()).name("Dense runestone").isEmpty(), 150, 25);
        } else {
            System.out.println("[DEBUG] Rotating to see essence");
            Camera.turnTo(essence);
        }
    }
}
