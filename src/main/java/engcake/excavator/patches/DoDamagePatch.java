package engcake.excavator.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.server.ServerClient;
import necesse.entity.DamagedObjectEntity;
import necesse.entity.TileDamageResult;
import necesse.entity.TileDamageType;
import necesse.level.gameObject.GameObject;
import necesse.level.maps.Level;
import necesse.level.maps.LevelObject;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(
        target = DamagedObjectEntity.class,
        name = "doDamage",
        arguments = {
                int.class,
                TileDamageType.class,
                int.class,
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
            @Advice.Argument(0) int damage,
            @Advice.Argument(1) TileDamageType type,
            @Advice.Argument(2) int toolTier,
            @Advice.Argument(3) ServerClient client,
            @Advice.Argument(4) boolean showEffects,
            @Advice.Argument(5) int mouseX,
            @Advice.Argument(6) int mouseY,
            @Advice.Return(readOnly = true)TileDamageResult result
            ) {
        if (client == null) {
            return;
        }

        Level level = client.getLevel();

        if (type != TileDamageType.Object || result == null || !result.destroyed) {
            return;
        }

        if (!shouldPropagate(result.levelObject.object)) {
            return;
        }

        int tileX = self.getTileX();
        int tileY = self.getTileY();

        LevelObject[] adjacentObjects = level.getAdjacentLevelObjects(tileX, tileY);
        for (LevelObject levelObject : adjacentObjects) {
            int currentTileX = levelObject.tileX;
            int currentTileY = levelObject.tileY;

            if (!shouldPropagate(levelObject.object) || (currentTileX != tileX && currentTileY != tileY)) {
                continue;
            }

            level.entityManager.doDamage(
                    levelObject.tileX,
                    levelObject.tileY,
                    levelObject.object.objectHealth,
                    type,
                    toolTier,
                    client,
                    showEffects,
                    mouseX,
                    mouseY
            );
        }
    }

    public static boolean shouldPropagate(GameObject gameObject) {
        return gameObject.isOre;
    }
}
