package rTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
import rTablets.Constants;
import rTablets.Task;

public class Mine extends Task {
    public Mine(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Players.local().animation() == -1
                && !Inventory.isFull()
                && Inventory.stream().filter(i -> i.name().equals("Dark essence block")).isEmpty();
    }

    @Override
    public void execute() {
        if (!Constants.essenceMineArea.contains(Players.local().tile())
                && !Players.local().inMotion()) {
            Movement.builder(Constants.essenceMineArea.getRandomTile())
                    .setRunMin(20).setRunMax(40)
                    .setWalkUntil(() -> ScriptManager.INSTANCE.isStopping() || ScriptManager.INSTANCE.isPaused())
                    .move();
        } else {
            GameObject essence = Objects.stream().name("Dense runestone").nearest().first();

            if (essence.inViewport()) {
                essence.finteract("Chip", "Dense runestone");
                Condition.wait(() -> Objects.stream().at(essence.tile()).name("Dense runestone").isEmpty(), 150, 25);
            } else {
                Camera.turnTo(essence);
            }
        }
    }
}
