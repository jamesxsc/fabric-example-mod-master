package uk.co.xsc.entities.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.Box;
import net.minecraft.client.model.Cuboid;


@Environment(EnvType.CLIENT)
public class GlobglogabgolabEntityModel<T extends Entity> extends EntityModel<T> {

        private final Cuboid head;
        private final Cuboid body;
        private final Cuboid arm1;
        private final Cuboid arm1top;
        private final Cuboid arm2;
        private final Cuboid arm2top;
        private final Cuboid tail;
        private final Cuboid tip;
        private final Cuboid mainl;
        private final Cuboid mainr;

        public GlobglogabgolabEntityModel() {
            textureWidth = 32;
            textureHeight = 32;

            head = new Cuboid(this);
            head.setRotationPoint(0.0F, -5.0F, -9.0F);
            setRotationAngle(head, 0.0F, -0.7854F, 0.0F);
            head.boxes.add(new Box(head, 1, 6, -3.0F, -9.0F, -3.0F, 6, 9, 7, 0.0F, false));

            body = new Cuboid(this);
            body.setRotationPoint(0.0F, 24.0F, 0.0F);
            setRotationAngle(body, 0.0F, 0.9599F, 0.0F);
            body.boxes.add(new Box(body, 6, 8, -5.0F, -1.0F, -9.0F, 16, 1, 15, 0.0F, false));
            body.boxes.add(new Box(body, 6, 8, -5.0F, -2.0F, -10.0F, 17, 1, 16, 0.0F, false));
            body.boxes.add(new Box(body, 0, 9, -6.0F, -8.0F, -11.0F, 19, 6, 17, 0.0F, false));
            body.boxes.add(new Box(body, 0, 9, -5.0F, -18.0F, -12.0F, 19, 10, 17, 0.0F, false));
            body.boxes.add(new Box(body, 1, 6, -3.0F, -23.0F, -10.0F, 15, 5, 13, 0.0F, false));
            body.boxes.add(new Box(body, 1, 6, -1.0F, -26.0F, -9.0F, 12, 3, 9, 0.0F, false));
            body.boxes.add(new Box(body, 0, 0, 1.0F, -29.0F, -9.0F, 10, 3, 8, 0.0F, false));

            arm1 = new Cuboid(this);
            arm1.setRotationPoint(0.0F, 24.0F, 0.0F);

            arm1top = new Cuboid(this);
            arm1top.setRotationPoint(4.0F, -25.0F, -8.0F);
            setRotationAngle(arm1top, 0.0F, 0.0F, 0.4363F);
            arm1.addChild(arm1top);
            arm1top.boxes.add(new Box(arm1top, 0, 0, 0.0F, -3.0F, -2.0F, 12, 3, 3, 0.0F, false));

            arm2 = new Cuboid(this);
            arm2.setRotationPoint(0.0F, 24.0F, 0.0F);

            arm2top = new Cuboid(this);
            arm2top.setRotationPoint(-4.0F, -25.0F, -7.0F);
            setRotationAngle(arm2top, 0.0F, 0.0F, -0.4363F);
            arm2.addChild(arm2top);
            arm2top.boxes.add(new Box(arm2top, 0, 0, -12.0F, -2.0F, -1.0F, 11, 3, 3, 0.0F, false));

            tail = new Cuboid(this);
            tail.setRotationPoint(0.0F, 24.0F, 0.0F);
            setRotationAngle(tail, -0.2618F, 0.0F, 0.0F);

            tip = new Cuboid(this);
            tip.setRotationPoint(0.0F, 0.0F, 0.0F);
            tail.addChild(tip);
            tip.boxes.add(new Box(tip, 1, 6, -2.0F, -4.0F, 7.0F, 4, 1, 7, 0.0F, false));

            mainl = new Cuboid(this);
            mainl.setRotationPoint(0.0F, 0.0F, 0.0F);
            setRotationAngle(mainl, 0.3491F, -0.6109F, -0.1745F);
            tail.addChild(mainl);
            mainl.boxes.add(new Box(mainl, 1, 6, 2.0F, -6.0F, 1.0F, 4, 6, 6, 0.0F, false));

            mainr = new Cuboid(this);
            mainr.setRotationPoint(0.0F, 0.0F, 0.0F);
            setRotationAngle(mainr, 0.2618F, 0.5236F, 0.0873F);
            tail.addChild(mainr);
            mainr.boxes.add(new Box(mainr, 1, 6, -5.0F, -6.0F, 3.0F, 4, 6, 6, 0.0F, false));
        }

        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
            head.render(f5);
            body.render(f5);
            arm1.render(f5);
            arm2.render(f5);
            tail.render(f5);
        }
        private void setRotationAngle(Cuboid cuboid, float x, float y, float z) {
            cuboid.pitch = x;
            cuboid.yaw = y;
            cuboid.roll = z;
        }
    

}