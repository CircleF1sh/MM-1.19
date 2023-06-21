package com.circle.magicalmarine.registry;

import com.circle.magicalmarine.MagicalMarine;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicalMarine.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMTab {
    public static CreativeModeTab MM_TAB;
    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        MM_TAB = event.registerCreativeModeTab(new ResourceLocation(MagicalMarine.MOD_ID, "mm_tab"),
                builder -> builder.icon(() -> new ItemStack(MMItems.SQUISH_SPAWN_EGG.get()))
                        .title(Component.translatable("creativemodetab.mm_tab")));
    }
}
