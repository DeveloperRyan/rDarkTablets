package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
import rDarkTablets.Constants;
import rDarkTablets.Task;
import rDarkTablets.Utils;

public class OpenLectern extends Task {
    public OpenLectern(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        // TODO: Check if player has runes
        final Component lecternWidget = Widgets.component(403, 0);

        return Players.local().animation() == -1
                && Inventory.stream().filter(i -> i.name().equals("Dark essence block")).isNotEmpty()
                && !lecternWidget.visible() && Constants.lecternArea.contains(Players.local().tile());
    }

    @Override
    public void execute() {
        GameObject lecternObject = Objects.stream().id(28802).nearest().first();
        final Component lecternWidget = Widgets.component(403, 0);

        // Need to make sure the inventory is opened as it is necessary to check if it's empty.
        if (lecternObject.inViewport() && Game.tab(Game.Tab.INVENTORY)) {
            System.out.println("[DEBUG] Interacting with lectern");

            if (lecternObject.interact("Study")) {
                System.out.println("[DEBUG] Waiting until lectern widget is visible");
                Condition.wait(() -> !Players.local().inMotion() && lecternWidget.visible(), 100, 15);
            }
        } else {
            System.out.println("[DEBUG] Rotating to see lectern");
            Camera.turnTo(lecternObject);
        }
    }
}
