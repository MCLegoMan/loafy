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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow public int itemId;
	@Shadow public int count;

	@Shadow private int damage;

	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/Item;II)V")
	private void init(Item count, int damage, int par3, CallbackInfo ci) {
		this.itemId = Item.BREAD.id;
	}
	@Inject(at = @At("RETURN"), method = "getItem", cancellable = true)
	private void getItem(CallbackInfoReturnable<Item> cir) {
		cir.setReturnValue(Item.ITEMS[Item.BREAD.id]);
	}
	@Inject(at = @At("HEAD"), method = "readNbt", cancellable = true)
	private void readNbt(NbtCompound nbt, CallbackInfo ci) {
		this.itemId = Item.BREAD.id;
		this.count = nbt.getByte("Count");
		this.damage = Item.BREAD.getMaxDamage();
		ci.cancel();
	}
	@Inject(at = @At("HEAD"), method = "writeNbt", cancellable = true)
	private void writeNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
		nbt.putShort("id", (short)Item.BREAD.id);
		nbt.putByte("Count", (byte)this.count);
		nbt.putShort("Damage", (short)Item.BREAD.getMaxDamage());
		cir.setReturnValue(nbt);
	}
	@Inject(at = @At("HEAD"), method = "getTranslationKey", cancellable = true)
	private void getTranslationKey(CallbackInfoReturnable<String> cir) {
		cir.setReturnValue("item.bread");
	}
}