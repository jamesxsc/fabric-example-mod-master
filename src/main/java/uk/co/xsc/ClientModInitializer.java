package uk.co.xsc;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.util.InputUtil;
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
                e.player.sendChatMessage("I love the Globglogabgolab!");
            }
        });
        keyBinding.getName();
        EntityRendererRegistry.INSTANCE.register(GlobglogabgolabEntity.class, ((entityRenderDispatcher, context) -> new GlobglogabgolabRenderer(entityRenderDispatcher)));
    }

}
