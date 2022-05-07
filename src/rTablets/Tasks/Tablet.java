package rTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
import rTablets.Constants;
import rTablets.Task;
import rTablets.Utils;

public class Tablet extends Task {
    public Tablet(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        // TODO: Check if player has runes
        return Players.local().animation() == -1
                && Inventory.stream().filter(i -> i.name().equals("Dark essence block")).isNotEmpty();
    }

    // TODO: Cleanup this function
    @Override
    public void execute() {
        if (!Constants.lecternArea.contains(Players.local().tile()) && !Players.local().inMotion()) {
            // We want to teleport if we're at the dark altar.
            if (Constants.enableTeleport) {
                if (Game.tab(Game.Tab.INVENTORY)
                        && Inventory.stream().filter(i -> i.name().toLowerCase().contains("library")).isNotEmpty()
                        && Constants.darkAltarArea.contains(Players.local().tile())) {
                    System.out.println("[DEBUG] Teleport tablet in inventory.");
                    Item arceuusTeleport = Inventory.stream().filter(i -> i.name().toLowerCase().contains("library")).first();

                    arceuusTeleport.interact("Break");
                    Condition.wait(() -> Players.local().animation() == -1, 200, 10);
                } else {
                    System.out.println("[DEBUG] Teleport tablet NOT in inventory.");
                }
            }
            Movement.builder(Constants.lecternArea.getRandomTile())
                    .setRunMin(20).setRunMax(40)
                    .setWalkUntil(() -> ScriptManager.INSTANCE.isStopping() || ScriptManager.INSTANCE.isPaused())
                    .move();
        } else {
            final Component lecternWidget = Widgets.component(403, 0);

            GameObject lecternObject = Objects.stream().id(28802).nearest().first();

            if (lecternWidget.visible()) {
                final Component lecternQuantityAllWidget = Widgets.component(403, 8);
                final Component createTabWidget = Widgets.component(403, 13);

                Component tabletWidget = Utils.getTabletWidget();

                // Make sure all creation settings are correct
                if (tabletWidget.component(0).textureId() != 897) {
                    tabletWidget.click();
                    Condition.sleep(Random.nextInt(500, 2000));
                }
                if (lecternQuantityAllWidget.component(0).textureId() != 897) {
                    lecternQuantityAllWidget.click();
                    Condition.sleep(Random.nextInt(500, 2000));
                }
                if (createTabWidget.visible() && createTabWidget.component(9).text().equals("Create")) {
                    createTabWidget.click();
                    Condition.sleep(Random.nextInt(500, 2000));
                }

                Condition.wait(() -> Players.local().animation() == -1
                                && Inventory.stream().filter(i -> i.name().toLowerCase().contains("dark essence")).isEmpty(),
                        500, 60);
            } else if (lecternObject.inViewport() && !lecternWidget.visible() && Players.local().animation() == -1) {
                Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 100, 5); // Open the inventory so bugs don't do the thing

                lecternObject.interact("Study");
                Condition.wait(() -> !Players.local().inMotion() && lecternWidget.visible(), 500, 5);
            } else {
                Camera.turnTo(lecternObject);
            }
        }
    }
}
