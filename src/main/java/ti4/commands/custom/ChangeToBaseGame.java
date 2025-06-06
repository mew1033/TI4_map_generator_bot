package ti4.commands.custom;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ti4.commands.GameStateSubcommand;
import ti4.helpers.Constants;
import ti4.helpers.Helper;
import ti4.image.Mapper;
import ti4.map.Game;
import ti4.message.MessageHelper;
import ti4.service.game.SetDeckService;

class ChangeToBaseGame extends GameStateSubcommand {

    public ChangeToBaseGame() {
        super(Constants.CHANGE_TO_BASE_GAME, "Remove Prophecy of Kings components from the game", true, true);
        addOptions(new OptionData(OptionType.STRING, Constants.REMOVE_CODEX_AC, "Remove Codex action cards too? (y/n)"));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Game game = getGame();
        OptionMapping codexOption = event.getOption(Constants.REMOVE_CODEX_AC);
        String codex = "";
        if (codexOption != null) {
            codex = codexOption.getAsString();
            if ("y".equalsIgnoreCase(codex)) {
                MessageHelper.sendMessageToChannel(event.getChannel(), "Removed Codex action cards.");
            }

        }
        game.setBaseGameMode(true);
        Helper.removePoKComponents(game, codex);
        SetDeckService.setDeck(event, game, "agenda_deck", Mapper.getDecks().get("agendas_base_game"));
        MessageHelper.sendMessageToChannel(event.getChannel(), "Removed Prophecy of Kings components.");
    }
}
