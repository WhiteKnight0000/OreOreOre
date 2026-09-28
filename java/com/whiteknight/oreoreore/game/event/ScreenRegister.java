package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.register.ModRitualItems;
import com.whiteknight.oreoreore.game.elements.screen.EarthAltarScreen;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

/**
 * ScreenとMenuの紐づけを登録する
 */
public class ScreenRegister {
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModRitualItems.EARTH_ALTAR_MENU.get(), EarthAltarScreen::new);
    }
}
