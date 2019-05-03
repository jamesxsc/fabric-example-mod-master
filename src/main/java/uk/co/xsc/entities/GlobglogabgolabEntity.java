package uk.co.xsc.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlobglogabgolabEntity extends PassiveEntity {

    private ServerBossBar bossBar;

    public GlobglogabgolabEntity(EntityType<? extends PassiveEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
        this.bossBar = new ServerBossBar(this.getDisplayName(), BossBar.Color.YELLOW, BossBar.Style.NOTCHED_10);
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(500D);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getHealthMaximum());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return super.getHurtSound(damageSource_1);
    }

    @Override
    protected SoundEvent getFallSound(int int_1) {
        return super.getFallSound(int_1);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    @Override
    public PassiveEntity createChild(PassiveEntity var1) {
        return null;
    }

}
