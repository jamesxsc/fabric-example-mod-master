package uk.co.xsc.commands;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;


public class CommandManager {

    public void init() {
//            dispatcher.register(net.minecraft.server.command.CommandManager.literal("lol")
//                    .then(
//                        argument("id", integer())
//                            .executes(c -> {
//                c.getSource().sendFeedback(new StringTextComponent("No lols have been created. :(").setStyle(new Style()), false);
//                return 1;
//            })
//                    )
//                    );
        CommandRegistry.INSTANCE.register(true, (dispatcher -> {
            dispatcher.register(
                    literal("foo")
                            .then(
                                    argument("bar", integer())
                                            .executes(c -> {
                                                System.out.println("Bar is " + getInteger(c, "bar"));
                                                return 1;
                                            })
                            )
                            .executes(c -> {
                                System.out.println("Called foo with no arguments");
                                return 1;
                            })
            );
        }));
    }

}
