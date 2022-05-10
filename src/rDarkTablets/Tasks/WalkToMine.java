package rDarkTablets.Tasks;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.Walking;
import org.powbot.mobile.script.ScriptManager;
import rDarkTablets.Constants;
import rDarkTablets.Task;

public class WalkToMine extends Task {
    public WalkToMine(String name) {
        super(name);
    }

    @Override
    public boolean activate() {

        return !Constants.essenceMineArea.contains(Players.local().tile())
                && Inventory.stream().filter(i -> i.name().equals("Dark essence block")).isEmpty()
                && !Inventory.isFull();
    }

    @Override
    public void execute() {
        System.out.println("[DEBUG] Walking to Mine");
        Movement.builder(Constants.essenceMineArea.getRandomTile())
                .setRunMin(20).setRunMax(50)
                .setWalkUntil(() -> ScriptManager.INSTANCE.isPaused() || ScriptManager.INSTANCE.isStopping())
                .move();
    }
}
