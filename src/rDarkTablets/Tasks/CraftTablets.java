package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;
import rDarkTablets.Task;
import rDarkTablets.Utils;

public class CraftTablets extends Task {
    public CraftTablets(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        final Component lecternWidget = Widgets.component(403, 0);
        return lecternWidget.visible();
    }

    @Override
    public void execute() {
        final Component lecternQuantityAllWidget = Widgets.component(403, 8);
        final Component createTabWidget = Widgets.component(403, 13);

        Component tabletWidget = Utils.getTabletWidget();


        // Checking to be sure all creation settings are correct
        if (tabletWidget.component(0).textureId() != 897) {
            tabletWidget.click();
            Condition.wait(() -> tabletWidget.component(0).textureId() == 897, 100, 10);
        }
        if (lecternQuantityAllWidget.component(0).textureId() != 897) {
            lecternQuantityAllWidget.click();
            Condition.wait(() -> lecternQuantityAllWidget.component(0).textureId() == 897, 100, 10);
        }
        if (createTabWidget.visible() && createTabWidget.component(9).text().equals("Create")) {
            createTabWidget.click();
            Condition.wait(() -> !tabletWidget.visible(), 100, 10);
        }

        Condition.wait(() -> Players.local().animation() == -1
                        && Inventory.stream().filter(i -> i.name().toLowerCase().contains("dark essence")).isEmpty(),
                500, 60);
    }
}
