package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import rDarkTablets.Constants;
import rDarkTablets.Task;

public class Teleport extends Task {
    public Teleport(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Constants.enableTeleport
                && Constants.DARK_ALTAR_AREA.contains(Players.local().tile())
                && Inventory.stream().id(Constants.ARCEUUS_LIBRARY_TELEPORT).isNotEmpty()
                && Inventory.stream().name(Constants.DARK_ESSENCE_BLOCK).isNotEmpty();
    }

    @Override
    public void execute() {
        Item teleportTablet = Inventory.stream().id(Constants.ARCEUUS_LIBRARY_TELEPORT).first();

        if (teleportTablet.valid()) { // Note: Make sure I need this check here. Technically checked in activate.
            System.out.println("[DEBUG] Breaking Teleport");

            if (teleportTablet.interact("Break")
                    && Condition.wait(() -> Players.local().animation() != -1, 100, 10)) { // Wait for teleport to start
                // Then wait for player to finish teleporting
                Condition.wait(() -> Constants.TELEPORT_AREA.contains(Players.local().tile()), 350, 10);
                System.out.println("[DEBUG] Teleported Successfully");
            } else {
                System.out.println("[DEBUG] Teleported Unsuccessfully");
            }
        } else {
            System.out.println("[DEBUG] No valid teleport");
        }
    }
}
