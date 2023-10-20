/*
    Loafy
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/loafy
    License: GNU LGPLv3
*/

package com.mclegoman.loafy.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Inject(at = @At("RETURN"), method = "getItem", cancellable = true)
	private void getItem(CallbackInfoReturnable<Item> cir) {
		cir.setReturnValue(Items.BREAD);
	}
}