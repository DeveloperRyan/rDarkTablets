package rTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
import rTablets.Constants;
import rTablets.Task;

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
        if (!Constants.darkAltarArea.contains(Players.local().tile()) && !Players.local().inMotion()) {
            Movement.builder(Constants.darkAltarArea.getRandomTile())
                    .setRunMin(20).setRunMax(40)
                    .setWalkUntil(() -> ScriptManager.INSTANCE.isStopping() || ScriptManager.INSTANCE.isPaused())
                    .move();
        } else {
            if (Game.tab(Game.Tab.INVENTORY)) {
                GameObject altar = Objects.stream().name("Dark Altar").nearest().first();

                if (altar.inViewport()) {
                    altar.interact("Venerate");
                    Condition.wait(() -> Inventory.stream().filter(i -> i.name().toLowerCase().contains("dense")).isEmpty(), 150, 10);
                } else {
                    Camera.turnTo(altar);
                }

            } else {
                Inventory.open();
                Condition.sleep(Random.nextInt(100, 300));
            }
        }
    }
}
