package net.thevortex8196.scythes.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.thevortex8196.scythes.Scythes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecialScytheItem extends ScytheItem{
    public SpecialScytheType type;

    private Entity thisLocked;

    static List<Entity> HeavenScytheLocked = new ArrayList<Entity>();

    static List<Entity> HellScytheLocked = new ArrayList<Entity>();

    public SpecialScytheItem(ToolMaterial material, Settings settings, SpecialScytheType type) {
        super(material, settings, -2.8f);
        this.type = type;
    }

    boolean addLock(List<Entity> list, Entity entity) {
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return false;

        if (list == HeavenScytheLocked) {
            addEntityToTeamWithGlow(entity, serverWorld, "heaven", Formatting.AQUA);
        } else if (list == HellScytheLocked) {
            addEntityToTeamWithGlow(entity, serverWorld, "hell", Formatting.DARK_RED);
        }

        return list.add(entity);
    }


    boolean removeLock(List<Entity> list, Entity entity) {
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return false;

        removeEntityFromTeamAndStopGlow(entity, serverWorld);
        return list.remove(entity);
    }


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Add the entity to the appropriate locked list if not already present
        boolean added = false;
        if (type == SpecialScytheType.HEAVEN) {
            added = addLock(HeavenScytheLocked, entity);
        } else if (type == SpecialScytheType.HELL) {
            added = addLock(HellScytheLocked, entity);
        }

        if (!(thisLocked == null)) {
            if (type == SpecialScytheType.HEAVEN) {
                removeLock(HeavenScytheLocked ,thisLocked);
            } else if (type == SpecialScytheType.HELL) {
                removeLock(HellScytheLocked ,thisLocked);
            }
        }

        thisLocked = entity;

        if (!added) {
            // Entity was already locked or type didn't match, fail early
            return ActionResult.FAIL;
        }

        // Find any entity locked by both Heaven and Hell scythes
        Entity doubleLockedEntity = null;
        for (Entity locked : HeavenScytheLocked) {
            if (HellScytheLocked.contains(locked)) {
                doubleLockedEntity = locked;
                break;
            }
        }

        // Remove the entity from both lists if double-locked
        if (doubleLockedEntity != null) {
            removeLock(HeavenScytheLocked ,thisLocked);
            removeLock(HellScytheLocked ,thisLocked);
        }

        Scythes.LOGGER.info("Double locked: " + doubleLockedEntity);

        return ActionResult.SUCCESS;
    }

    public void createGlowTeamIfNeeded(ServerWorld world, String name, Formatting color) {
        Scoreboard scoreboard = world.getScoreboard();
        if (scoreboard.getTeam(name) == null) {
            Team team = scoreboard.addTeam(name);
            team.setDisplayName(Text.literal(name));
            team.setColor(color);
            team.setShowFriendlyInvisibles(false);
            team.setFriendlyFireAllowed(true);
        }
    }

    public void addEntityToTeamWithGlow(Entity entity, ServerWorld world, String teamName, Formatting color) {
        createGlowTeamIfNeeded(world, teamName, color);
        Scoreboard scoreboard = world.getScoreboard();
        Team team = scoreboard.getTeam(teamName);
        if (team != null) {
            scoreboard.addScoreHolderToTeam(entity.getUuidAsString(), team);
        }
        entity.setGlowing(true);
    }

    public void removeEntityFromTeamAndStopGlow(Entity entity, ServerWorld world) {
        entity.setGlowing(false);
        Scoreboard scoreboard = world.getScoreboard();

        Team currentTeam = entity.getScoreboardTeam(); //✔️ Correct method
        if (currentTeam != null) {
            scoreboard.removeScoreHolderFromTeam(entity.getUuidAsString(), currentTeam);
        }
    }
}

