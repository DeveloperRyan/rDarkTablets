package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
import rDarkTablets.Constants;
import rDarkTablets.Task;

public class Convert extends Task {
    public Convert(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Players.local().animation() == -1 && Inventory.isFull()
                && Inventory.stream().filter(i -> i.name().toLowerCase().contains("dense")).isNotEmpty();
    }

    @Override
    public void execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            GameObject altar = Objects.stream().name("Dark Altar").nearest().first();
            System.out.println("[DEBUG] Finding dark altar");

            if (altar.inViewport()) {
                System.out.println("[DEBUG] Interacting with dark altar");
                altar.interact("Venerate");
                Condition.wait(() -> Inventory.stream().filter(i -> i.name().toLowerCase().contains("dense")).isEmpty(), 300, 10);
            } else {
                System.out.println("[DEBUG] Rotating to see dark altar");
                Camera.turnTo(altar);
            }
        }
    }
}
