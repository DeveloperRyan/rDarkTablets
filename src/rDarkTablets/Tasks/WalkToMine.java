package rDarkTablets.Tasks;

import org.powbot.api.Tile;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.local.Flag;
import org.powbot.mobile.script.ScriptManager;
import rDarkTablets.Constants;
import rDarkTablets.Task;

public class WalkToMine extends Task {
    public WalkToMine(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return !Constants.ESSENCE_MINE_AREA.contains(Players.local().tile())
                && Inventory.stream().name(Constants.DARK_ESSENCE_BLOCK).isEmpty()
                && !Inventory.isFull();
    }

    @Override
    public void execute() {
        System.out.println("[DEBUG] Walking to Mine");

        Movement.builder(Constants.ESSENCE_MINE_WALKABLE.getRandomTile())
                .setRunMin(20).setRunMax(50)
                .setWalkUntil(() -> ScriptManager.INSTANCE.isPaused() || ScriptManager.INSTANCE.isStopping())
                .move();
    }
}
