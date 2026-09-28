package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.register.ModOres;
import com.whiteknight.oreoreore.game.register.ModPotions;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

public class ModBrewingRecipes {
    public static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(
                Potions.AWKWARD,
                ModOres.SILVER_NUGGET.get(),
                ModPotions.HOLY_POTION
        );
        event.getBuilder().addMix(
                Potions.WATER_BREATHING,
                ModOres.AQUAMARINE.get(),
                ModPotions.WATER_BREATHING_POTION_12MIN
        );
        event.getBuilder().addMix(
                Potions.LONG_WATER_BREATHING,
                ModOres.AQUAMARINE.get(),
                ModPotions.WATER_BREATHING_POTION_12MIN
        );

    }
}
