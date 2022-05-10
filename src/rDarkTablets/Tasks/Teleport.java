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
                && Constants.darkAltarArea.contains(Players.local().tile())
                && Inventory.stream().filter(i -> i.name().toLowerCase().contains("library")).isNotEmpty()
                && Inventory.stream().filter(i -> i.name().equals("Dark essence block")).isNotEmpty();
    }

    @Override
    public void execute() {
        Item teleportTablet = Inventory.stream().filter(i -> i.name().toLowerCase().contains("library")).first();

        if (teleportTablet.valid()) { // Note: Make sure I need this check here. Technically checked in activate.
            System.out.println("[DEBUG] Breaking Teleport");

            if (teleportTablet.interact("Break")
                    && Condition.wait(() -> Players.local().animation() != -1, 100, 10)) { // Wait for teleport to start
                // Then wait for player to finish teleporting
                Condition.wait(() -> Constants.teleportArea.contains(Players.local().tile()), 350, 10);
                System.out.println("[DEBUG] Teleported Successfully");
            } else {
                System.out.println("[DEBUG] Teleported Unsuccessfully");
            }
        } else {
            System.out.println("[DEBUG] No valid teleport");
        }
    }
}
