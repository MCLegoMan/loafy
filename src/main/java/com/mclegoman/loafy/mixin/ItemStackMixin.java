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
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow public int count;
	@Shadow private int damage;
	@Shadow public NbtCompound nbt;
	@Shadow private Item item;
	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/item/Item;II)V")
	private void init(Item i, int j, int par3, CallbackInfo ci) {
		this.item = Items.BREAD;
		this.damage = Items.BREAD.getMaxDamage();
	}
	@Inject(at = @At("RETURN"), method = "getItem", cancellable = true)
	private void getItem(CallbackInfoReturnable<Item> cir) {
		cir.setReturnValue(Items.BREAD);
	}
	@Inject(at = @At("HEAD"), method = "toNbt", cancellable = true)
	private void readNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
		nbt.putShort("id", (short)Item.getRawId(Items.BREAD));
		nbt.putByte("Count", (byte)this.count);
		nbt.putShort("Damage", (short)Items.BREAD.getMaxDamage());
		if (this.nbt != null) nbt.put("tag", this.nbt);
		cir.setReturnValue(nbt);
	}
	@Inject(at = @At("HEAD"), method = "writeNbt", cancellable = true)
	private void writeNbt(NbtCompound nbt, CallbackInfo ci) {
		this.item = Items.BREAD;
		this.count = nbt.getByte("Count");
		this.damage = Items.BREAD.getMaxDamage();
		if (this.damage < 0) this.damage = 0;
		if (nbt.contains("tag", 10)) this.nbt = nbt.getCompound("tag");
		ci.cancel();
	}
}