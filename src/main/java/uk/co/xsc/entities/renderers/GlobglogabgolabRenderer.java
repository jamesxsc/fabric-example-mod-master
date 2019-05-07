package uk.co.xsc.entities.renderers;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import uk.co.xsc.entities.GlobglogabgolabEntity;
import uk.co.xsc.entities.models.GlobglogabgolabEntityModel;

public class GlobglogabgolabRenderer extends MobEntityRenderer<GlobglogabgolabEntity, GlobglogabgolabEntityModel<GlobglogabgolabEntity>> {

    public GlobglogabgolabRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new GlobglogabgolabEntityModel<>(), 1);
    }

    @Override
    protected Identifier getTexture(GlobglogabgolabEntity var1) {
        return new Identifier("test_mod", "just-no");
    }

}
