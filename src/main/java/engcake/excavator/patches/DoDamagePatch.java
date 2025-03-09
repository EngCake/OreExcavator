package engcake.excavator.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.server.ServerClient;
import necesse.entity.DamagedObjectEntity;
import necesse.entity.ObjectDamageResult;
import necesse.entity.mobs.Attacker;
import necesse.level.gameObject.GameObject;
import necesse.level.maps.Level;
import necesse.level.maps.LevelObject;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(
        target = DamagedObjectEntity.class,
        name = "doObjectDamage",
        arguments = {
                int.class,
                int.class,
                float.class,
                Attacker.class,
                ServerClient.class,
                boolean.class,
                int.class,
                int.class
        }
)
public class DoDamagePatch {
    @Advice.OnMethodExit
    static void onExit(
            @Advice.This DamagedObjectEntity self,
            @Advice.Argument(0) int objectLayerID,
            @Advice.Argument(1) int damage,
            @Advice.Argument(2) float toolTier,
            @Advice.Argument(3) Attacker attacker,
            @Advice.Argument(4) ServerClient client,
            @Advice.Argument(5) boolean showEffects,
            @Advice.Argument(6) int mouseX,
            @Advice.Argument(7) int mouseY,
            @Advice.Return ObjectDamageResult result) {
        if (shouldSkipProcessing(client, result)) {
            return;
        }

        Level level = client.getLevel();
        int tileX = self.getTileX();
        int tileY = self.getTileY();

        if (isOre(result.levelObject.object)) {
            checkAdjacentObjects(level, objectLayerID, tileX, tileY, toolTier, attacker, client, showEffects, mouseX, mouseY);
        }
    }

    public static boolean shouldSkipProcessing(ServerClient client, ObjectDamageResult result) {
        return client == null || result == null || !result.destroyed;
    }

    public static boolean isOre(GameObject gameObject) {
        return gameObject.isOre;
    }

    public static void checkAdjacentObjects(Level level, int objectLayerID, int tileX, int tileY, float toolTier, Attacker attacker, ServerClient client, boolean showEffects, int mouseX, int mouseY) {
        LevelObject[] adjacentObjects = level.getAdjacentLevelObjects(tileX, tileY);

        for (LevelObject levelObject : adjacentObjects) {
            if (isAdjacentOre(levelObject, tileX, tileY)) {
                damageAdjacentOre(level, objectLayerID, levelObject, toolTier, attacker, client, showEffects, mouseX, mouseY);
            }
        }
    }

    public static boolean isAdjacentOre(LevelObject levelObject, int tileX, int tileY) {
        return isOre(levelObject.object) && (levelObject.tileX == tileX || levelObject.tileY == tileY);
    }

    public static void damageAdjacentOre(Level level, int objectLayerID, LevelObject levelObject, float toolTier, Attacker attacker, ServerClient client, boolean showEffects, int mouseX, int mouseY) {
        level.entityManager.doObjectDamage(
                objectLayerID,
                levelObject.tileX,
                levelObject.tileY,
                levelObject.object.objectHealth,
                toolTier,
                attacker,
                client,
                showEffects,
                mouseX,
                mouseY
        );
    }
}
