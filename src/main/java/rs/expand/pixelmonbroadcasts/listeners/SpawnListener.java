// Listens for Pokémon spawns on the Better Spawner.
package rs.expand.pixelmonbroadcasts.listeners;

// Remote imports.
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// Local imports.
import static rs.expand.pixelmonbroadcasts.PixelmonBroadcasts.*;
import static rs.expand.pixelmonbroadcasts.utilities.PrintingMethods.*;
import static rs.expand.pixelmonbroadcasts.utilities.PlaceholderMethods.*;

public class SpawnListener
{
    @SubscribeEvent
    public void onSpawnPokemonEvent(final SpawnEvent event)
    {
        // See which entity was spawned.
        final Entity spawnedEntity = event.action.getOrCreateEntity();

        // Check if the entity is a Pokémon, not a trainer or the like.
        if (spawnedEntity instanceof EntityPixelmon)
        {
            // Make an assumption. This is safe, now.
            final EntityPixelmon pokemon = (EntityPixelmon) spawnedEntity;

            // Make sure this Pokémon has no owner -- it has to be wild.
            // I put bosses under this check, as well. Who knows what servers cook up for player parties?
            if (!pokemon.hasOwner())
            {
                final String pokemonName = pokemon.getLocalizedName();
                final World world = pokemon.getEntityWorld();
                final BlockPos location = event.action.spawnLocation.location.pos;

                if (pokemon.isBossPokemon())
                {
                    if (logBossSpawns)
                    {
                        // Print a spawn message to console.
                        printBasicMessage
                        (
                                "§5PBR §f// §eA boss §6" + pokemonName +
                                "§e has spawned in world \"§6" + world.getWorldInfo().getWorldName() +
                                "§e\", at X:§6" + location.getX() +
                                "§e Y:§6" + location.getY() +
                                "§e Z:§6" + location.getZ()
                        );
                    }

                    if (showBossSpawns)
                    {
                        // Parse placeholders and print! Pass a null object for the player, so our receiving method knows to ignore.
                        if (bossSpawnMessage != null)
                        {
                            // Set up our message. This is the same for all eligible players, so call it once and store it.
                            final String finalMessage = replacePlaceholders(
                                    bossSpawnMessage, null, false, false, pokemon, location);

                            // Send off the message, the needed notifier permission and the flag to check.
                            iterateAndSendEventMessage(finalMessage, pokemon, hoverBossSpawns, true,
                                    false, "bossspawn", "showBossSpawn");
                        }
                        else
                            printBasicError("The boss spawn message is broken, broadcast failed.");
                    }
                }
                else if (EnumPokemon.legendaries.contains(pokemonName) && pokemon.getIsShiny())
                {
                    if (logShinyLegendarySpawns)
                    {
                        // Print a spawn message to console.
                        printBasicMessage
                        (
                                "§5PBR §f// §aA shiny legendary §2" + pokemonName +
                                "§a has spawned in world \"§2" + world.getWorldInfo().getWorldName() +
                                "§a\", at X:§2" + location.getX() +
                                "§a Y:§2" + location.getY() +
                                "§a Z:§2" + location.getZ()
                        );
                    }

                    if (showShinyLegendarySpawns)
                    {
                        // Parse placeholders and print! Pass a null object for the player, so our receiving method knows to ignore.
                        if (shinyLegendarySpawnMessage != null)
                        {
                            // Set up our message. This is the same for all eligible players, so call it once and store it.
                            // We use the normal legendary permission for shiny legendaries, as per the config's explanation.
                            final String finalMessage = replacePlaceholders(
                                    shinyLegendarySpawnMessage, null, false, false, pokemon, location);

                            // Send off the message, the needed notifier permission and the flag to check.
                            iterateAndSendEventMessage(finalMessage, pokemon, hoverShinyLegendarySpawns, true,
                                    false, "shinylegendaryspawn", "showShinyLegendarySpawn");
                        }
                        else
                            printBasicError("The shiny legendary spawn message is broken, broadcast failed.");
                    }
                }
                else if (EnumPokemon.legendaries.contains(pokemonName))
                {
                    if (logLegendarySpawns)
                    {
                        // Print a spawn message to console.
                        printBasicMessage
                        (
                                "§5PBR §f// §aA legendary §2" + pokemonName +
                                "§a has spawned in world \"§2" + world.getWorldInfo().getWorldName() +
                                "§a\", at X:§2" + location.getX() +
                                "§a Y:§2" + location.getY() +
                                "§a Z:§2" + location.getZ()
                        );
                    }

                    if (showLegendarySpawns)
                    {
                        // Parse placeholders and print! Pass a null object for the player, so our receiving method knows to ignore.
                        if (legendarySpawnMessage != null)
                        {
                            // Set up our message. This is the same for all eligible players, so call it once and store it.
                            final String finalMessage = replacePlaceholders(
                                    legendarySpawnMessage, null, false, false, pokemon, location);

                            // Send off the message, the needed notifier permission and the flag to check.
                            iterateAndSendEventMessage(finalMessage, pokemon, hoverLegendarySpawns, true,
                                    false, "legendaryspawn", "showLegendarySpawn");
                        }
                        else
                            printBasicError("The legendary spawn message is broken, broadcast failed.");
                    }
                }
                else if (pokemon.getIsShiny())
                {
                    if (logShinySpawns)
                    {
                        // Print a spawn message to console.
                        printBasicMessage
                        (
                                "§5PBR §f// §bA shiny §3" + pokemonName +
                                "§b has spawned in world \"§3" + world.getWorldInfo().getWorldName() +
                                "§b\", at X:§3" + location.getX() +
                                "§b Y:§3" + location.getY() +
                                "§b Z:§3" + location.getZ()
                        );
                    }

                    if (showShinySpawns)
                    {
                        // Parse placeholders and print! Pass a null object for the player, so our receiving method knows to ignore.
                        if (shinySpawnMessage != null)
                        {
                            // Set up our message. This is the same for all eligible players, so call it once and store it.
                            final String finalMessage = replacePlaceholders(
                                    shinySpawnMessage, null, false, false, pokemon, location);

                            // Send off the message, the needed notifier permission and the flag to check.
                            iterateAndSendEventMessage(finalMessage, pokemon, hoverShinySpawns, true,
                                    false, "shinyspawn", "showShinySpawn");
                        }
                        else
                            printBasicError("The shiny spawn message is broken, broadcast failed.");
                    }
                }
            }
        }
    }
}
