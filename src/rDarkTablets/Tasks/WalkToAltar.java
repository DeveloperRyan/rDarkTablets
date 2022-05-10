package rDarkTablets.Tasks;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.mobile.script.ScriptManager;
import rDarkTablets.Constants;
import rDarkTablets.Task;

public class WalkToAltar extends Task {
    public WalkToAltar(String name) {
        super(name);
    }

    @Override
    public boolean activate() {

        return !Constants.darkAltarArea.contains(Players.local().tile())
                && Inventory.stream().filter(i -> i.name().equals("Dense essence block")).isNotEmpty()
                && Inventory.isFull();
    }

    @Override
    public void execute() {
        System.out.println("[DEBUG] Walking to Altar");
        Movement.builder(Constants.darkAltarArea.getRandomTile())
                .setRunMin(20).setRunMax(50)
                .setWalkUntil(() -> ScriptManager.INSTANCE.isPaused() || ScriptManager.INSTANCE.isStopping())
                .move();
    }
}
