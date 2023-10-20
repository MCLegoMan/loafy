/*
    Loafy
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/loafy
    License: GNU LGPLv3
*/

package com.mclegoman.loafy.mixin;

import net.minecraft.Item;
import net.minecraft.class_142;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_142.class)
public abstract class class_142Mixin {
	@Shadow public ItemStack field_564;
	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)V")
	private void init(World d, double e, double f, double arg2, ItemStack par5, CallbackInfo ci) {
		this.field_564 = new ItemStack(Item.BREAD);
	}
}