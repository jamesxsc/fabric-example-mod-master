package uk.co.xsc.commands;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableTextComponent;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public class CommandManager {

    public void init() {

        System.out.println("Initializing command system!");

//            dispatcher.register(net.minecraft.server.command.CommandManager.literal("lol")
//                    .then(
//                        argument("id", integer())
//                            .executes(c -> {
//                c.getSource().sendFeedback(new StringTextComponent("No lols have been created. :(").setStyle(new Style()), false);
//                return 1;
//            })
//                    )
//                    );

        CommandRegistry.INSTANCE.register(false, (dispatcher ->
                dispatcher.register(
                        literal("lol")
                                .then(
                                        argument("id", integer())
                                                .executes(c -> {
                                                    c.getSource().sendFeedback(
                                                            new TranslatableTextComponent("test_mod.command.feedback.lol.no-lols")
                                                                    .setStyle(new Style()), false);
                                                    return 1;
                                                })
                                )
                                .executes(c -> {
                                    //todo random lols
                                    return 1;
                                })
                )));
    }

}
