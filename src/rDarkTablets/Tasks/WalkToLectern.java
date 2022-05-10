package rDarkTablets.Tasks;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.mobile.script.ScriptManager;
import rDarkTablets.Constants;
import rDarkTablets.Task;

public class WalkToLectern extends Task {
    public WalkToLectern(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return !Constants.lecternArea.contains(Players.local().tile())
                && Inventory.stream().filter(i -> i.name().equals("Dark essence block")).isNotEmpty();
    }

    @Override
    public void execute() {
        System.out.println("[DEBUG] Walking to Lectern");
        Movement.builder(Constants.lecternArea.getRandomTile())
                .setRunMin(20).setRunMax(50)
                .setWalkUntil(() -> ScriptManager.INSTANCE.isPaused() || ScriptManager.INSTANCE.isStopping())
                .move();
    }
}
