package ti4.commands.relic;

import java.util.List;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import ti4.commands.GameStateSubcommand;
import ti4.helpers.Constants;
import ti4.image.Mapper;
import ti4.map.Game;
import ti4.map.Player;
import ti4.message.MessageHelper;
import ti4.model.RelicModel;

class RelicLookAtTop extends GameStateSubcommand {

    public RelicLookAtTop() {
        super(Constants.RELIC_LOOK_AT_TOP, "Privately look at the top of the relic deck.", false, true);
    }

    public void execute(SlashCommandInteractionEvent event) {
        Game game = getGame();
        Player player = getPlayer();
        List<String> relicDeck = game.getAllRelics();
        if (relicDeck.isEmpty()) {
            MessageHelper.sendMessageToEventChannel(event, "Relic deck is empty");
            return;
        }
        String relicID = relicDeck.getFirst();
        RelicModel relicModel = Mapper.getRelic(relicID);
        String sb = "**Relic - Look at Top**\n" + player.getRepresentation() + "\n" + relicModel.getSimpleRepresentation();
        MessageHelper.sendMessageToPlayerCardsInfoThread(player, sb);
    }
}
