package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
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
                && !lecternWidget.visible() && Constants.LECTERN_AREA.contains(Players.local().tile());
    }

    @Override
    public void execute() {
        GameObject lecternObject = Objects.stream().id(Constants.LECTERN_ID).within(Constants.LECTERN_AREA).first();

        // Need to make sure the inventory is opened as it is necessary to check if it's empty.
        if (lecternObject.valid() && lecternObject.inViewport() && Game.tab(Game.Tab.INVENTORY)) {
            System.out.println("[DEBUG] Interacting with lectern");

            if (lecternObject.interact("Study")) {
                System.out.println("[DEBUG] Waiting until lectern widget is visible");
                Condition.wait(() -> !Players.local().inMotion() && Utils.getMainLecternWidget().visible(), 100, 15);
            }
        } else {
            System.out.println("[DEBUG] Rotating to see lectern");
            Camera.turnTo(lecternObject);
        }
    }
}
