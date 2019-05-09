package uk.co.xsc;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextFormat;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import uk.co.xsc.entities.GlobglogabgolabEntity;
import uk.co.xsc.entities.renderers.GlobglogabgolabRenderer;

public class ClientModInitializer implements net.fabricmc.api.ClientModInitializer {

    private static FabricKeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        keyBinding = FabricKeyBinding.Builder.create(new Identifier("test_mod", "keybind"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "Hidden Gems!"
        ).build();
        KeyBindingRegistry.INSTANCE.register(keyBinding);
        ClientTickCallback.EVENT.register(e -> {
            if (keyBinding.isPressed()) {
                e.player.addChatMessage(new StringTextComponent("You just pressed " + keyBinding.getName()).setStyle(new Style().setColor(TextFormat.AQUA).setItalic(true)), false);
                e.player.sendChatMessage("I love the Globglogabgolab!");
                //e.player.sendChatMessage(e.world.getBlockState(new BlockPos(e.player.rayTrace(5,10,true).getPos())).getOutlineShape(null, new BlockPos(e.player.rayTrace(5,10,true).getPos())).simplify().toString());
            }
        });
        keyBinding.getName();
        EntityRendererRegistry.INSTANCE.register(GlobglogabgolabEntity.class, ((entityRenderDispatcher, context) -> new GlobglogabgolabRenderer(entityRenderDispatcher)));
    }

}
