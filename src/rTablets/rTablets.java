package rTablets;

import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackInventoryOption;
import org.powbot.api.script.paint.TrackSkillOption;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;
import rTablets.Tasks.Convert;
import rTablets.Tasks.Mine;
import rTablets.Tasks.Tablet;

import java.util.ArrayList;

import org.powbot.api.Random;

@ScriptManifest(name = "rTablets", description = "Creates tablets in Arceuus.",
        version = "0.1.0", category = ScriptCategory.MoneyMaking)

@ScriptConfiguration.List(
        {
                @ScriptConfiguration(name = "Select Tablet",
                        description = "What tablet would you like to create?",
                        defaultValue = "Arceuus library teleport",
                        allowedValues = {"Arceuus library teleport", "Draynor manor teleport", "Battlefront teleport",
                                "Mind altar teleport", "Salve graveyard teleport", "Fenkenstrain's castle teleport", "West ardougne teleport",
                                "Harmony island teleport", "Cemetery teleport", "Barrows teleport", "Ape atoll teleport"},
                        optionType = OptionType.STRING
                ),
                @ScriptConfiguration(name = "Library Teleport",
                        description = "Enable library teleport, must have library teleport tablets in inventory.",
                        optionType = OptionType.BOOLEAN)
        }
)

public class rTablets extends AbstractScript {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private Task currentTask = null;

    /*
    Script Tasks
    --------------
    Precheck: Check player is in area, has pick-axe & chisel
    1) Go to Dense essence mine : Mine until inventory is full
    2) Go to dark altar, convert essence to dark essence : Until all essence converted
    3) Go to lectern, convert tablets : Until all essence are converted
    REPEAT FROM HERE
     */

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("rTablets", "", "127.0.0.1:5685", true, false);
    }


    @Override
    public void onStart() {
        // Two required items for this script: Pickaxe, chisel
        Item pickaxe = Inventory.stream().filter(i -> i.name().toLowerCase().contains("pickaxe")).first();
        Item chisel = Inventory.stream().name("Chisel").first();

        Constants.tabletName = getOption("Select Tablet");
        Constants.enableTeleport = getOption("Library Teleport");

        // Make sure we have pickaxe + chisel
        if (pickaxe.valid() && chisel.valid()) {
            taskList.add(new Mine("Mine"));
            taskList.add(new Convert("Convert"));
            taskList.add(new Tablet("Tablet"));
        } else {
            ScriptManager.INSTANCE.stop();
        }

        Paint paint = PaintBuilder.newBuilder()
                .x(40)
                .y(45)
                .addString("Status: ", () -> currentTask.name)
                .trackInventoryItem(Inventory.stream().filter(i -> i.name().equals(Constants.tabletName)).first().id(), "Tablets", TrackInventoryOption.Price, TrackInventoryOption.QuantityChange)
                .trackSkill(Skill.Magic, TrackSkillOption.Exp)
                .trackSkill(Skill.Mining, TrackSkillOption.Exp)
                .trackSkill(Skill.Crafting, TrackSkillOption.Exp)
                .trackSkill(Skill.Runecrafting, TrackSkillOption.Exp)
                .build();
        addPaint(paint);

        System.out.println("[DEBUG] CONFIGURATION | Tablet: " + Constants.tabletName + " | Teleport: " + Constants.enableTeleport);
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                currentTask = task;
                task.execute();
                if (ScriptManager.INSTANCE.isStopping()) {
                    break;
                }
            }
        }

        if (Random.nextInt(0, 500) == 1) {
            if (Random.nextBoolean()) {
                Camera.angle(Random.nextInt(0, 359));
            }
        }
    }
}
