package com.circle.magicalmarine.common.events;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.registry.MMItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicalMarine.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    @SubscribeEvent
    public static void addWanderingTraderTrades(WandererTradesEvent event) {
            event.getGenericTrades().add(new BasicItemListing(2, new ItemStack(MMItems.SQUISHSPAWN.get()), 6, 3, 0.4f));
            System.out.println("MagicalMarine Wandering Trade Successful!");
    }
}
