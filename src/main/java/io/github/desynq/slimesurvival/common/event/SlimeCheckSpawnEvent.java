package io.github.desynq.slimesurvival.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.neoforged.bus.api.Event;

public class SlimeCheckSpawnEvent extends Event {

    private final EntityType<Slime> slime;
    private final LevelAccessor level;
    private final MobSpawnType spawnType;
    private final BlockPos pos;
    private final RandomSource random;
    private Result result = Result.DEFAULT;

    public SlimeCheckSpawnEvent(
            EntityType<Slime> slime,
            LevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random
    ) {
        super();
        this.slime = slime;
        this.level = level;
        this.spawnType = spawnType;
        this.pos = pos;
        this.random = random;
    }

    public EntityType<Slime> getSlime() {
        return slime;
    }

    public LevelAccessor getLevel() {
        return level;
    }

    public MobSpawnType getSpawnType() {
        return spawnType;
    }

    public BlockPos getPos() {
        return pos;
    }

    public RandomSource getRandom() {
        return random;
    }

    public boolean isNotPeaceful() {
        return level.getDifficulty() != Difficulty.PEACEFUL;
    }

    public boolean isSurfaceSpawnable(int minY, int maxY) {
        return level.getBiome(pos).is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
                && pos.getY() > minY
                && pos.getY() < maxY
                && random.nextFloat() < 0.5F
                && random.nextFloat() < level.getMoonBrightness()
                && level.getMaxLocalRawBrightness(pos) <= random.nextInt(8);
    }

    public boolean isSurfaceSpawnable() {
        return isSurfaceSpawnable(50, 70);
    }

    public boolean checkMobSpawnRules() {
        return Mob.checkMobSpawnRules(slime, level, spawnType, pos, random);
    }

    public boolean isSlimeChunk() {
        if (level instanceof WorldGenLevel worldGenLevel) {
            ChunkPos chunkpos = new ChunkPos(pos);
            return WorldgenRandom
                    .seedSlimeChunk(chunkpos.x, chunkpos.z, worldGenLevel.getSeed(), 987234911L)
                    .nextInt(10) == 0;
        }
        return false;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static enum Result {
        ALLOW,
        DEFAULT,
        DENY
    }
}
